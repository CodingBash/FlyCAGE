package edu.ilstu.biology.flytranscriptionwebapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.biology.flytranscriptionwebapp.model.ExpressionStageOptions;
import edu.ilstu.biology.flytranscriptionwebapp.model.FinalResponseCorrelationResult;
import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;
import edu.ilstu.biology.flytranscriptionwebapp.model.PairwiseCorrelationDataAjaxRequestBody;
import edu.ilstu.biology.flytranscriptionwebapp.model.PairwiseGeneCorrelationData;
import edu.ilstu.biology.flytranscriptionwebapp.processor.ExpressionStageOptionsGenerator;
import edu.ilstu.biology.flytranscriptionwebapp.processor.ExpressionStageProcessor;
import edu.ilstu.biology.flytranscriptionwebapp.processor.GenomicCorrelationAnalysis;
import edu.ilstu.biology.flytranscriptionwebapp.processor.RetrieveCorrelationData;
import edu.ilstu.biology.flytranscriptionwebapp.processor.RetrieveExpressionStages;
import edu.ilstu.biology.flytranscriptionwebapp.validation.InvalidGeneException;

@Controller
public class OutputController {

	@Autowired
	private GenomicCorrelationAnalysis correlationAnalysis;

	@Autowired
	private RetrieveCorrelationData retrieveCorrelationData;

	@Autowired
	private RetrieveExpressionStages retrieveExpressionStages;

	@Autowired
	private ExpressionStageOptionsGenerator expressionStageOptionsGenerator;

	private static final String INPUT_GENE_NAME = "INPUT GENE";
	private static final String CUSTOM_GENE_SESSION_NAME = "CUSTOM_GENE";

	// TODO: Remove business logic from controller method
	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public ModelAndView processOutput(@ModelAttribute("geneForm") GeneForm geneForm, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("selectedExpressionStageLabels",
				retrieveSelectedExpressionLabels(geneForm.getExpressionStages()));

		FinalResponseCorrelationResult result = null;
		try {
			Gene customGene = retrieveCustomGene(geneForm.getCustomExpression());
			List<Integer> selectedExpressionIndices = ExpressionStageProcessor.selectedExpressionStageIndices(
					geneForm.getExpressionStages(), retrieveExpressionStages.getDmelanogasterExpressionStages());
			List<String> geneOfInterestList = retrieveGeneOfInterestList(geneForm.getGenesOfInterest());
			result = correlationAnalysis.retrieveMrnaCorrelationResults(customGene, geneForm.getInputIdentifier(),
					selectedExpressionIndices, geneOfInterestList, geneForm.getGeneResultCount());
			// TODO: I dont like session solution, change to request solution
			session.setAttribute(CUSTOM_GENE_SESSION_NAME, customGene);
		} catch (InvalidGeneException ige) {
			// TODO: Consider a redirect to "GET /" w/ RedirectAttribute to
			// switch URL
			mav.addObject("exception", ige);
			ExpressionStageOptions expressionStageOptions = expressionStageOptionsGenerator
					.generateExpressionStageOptions();
			mav.addObject("expressionStageOptions", expressionStageOptions);
			mav.setViewName("input");
			return mav;
		}

		mav.addObject("result", result);
		mav.addObject("expressionStageOptions", expressionStageOptionsGenerator.generateExpressionStageOptions());
		// TODO: Eventually map the geneForm to a better Gene object
		mav.addObject("geneForm", geneForm);
		mav.setViewName("output");
		return mav;
	}

	/*
	 * TODO: Only accept and send targetgene data. Front end should have access
	 * of inputgene data for optimization
	 * 
	 * TODO: Does this data needs to be normalized? Or do that on the front-end?
	 */
	@PostMapping("/pairwise-correlation-data")
	public ResponseEntity<PairwiseGeneCorrelationData> getSearchResultViaAjax(
			@RequestBody PairwiseCorrelationDataAjaxRequestBody body, HttpSession session) {
		PairwiseGeneCorrelationData pData = new PairwiseGeneCorrelationData();
		List<Integer> selectedIndices = ExpressionStageProcessor.selectedExpressionStageIndices(
				body.getSelectedExpressionStages(), retrieveExpressionStages.getDmelanogasterExpressionStages());
		if (body.getInputGene().equals(INPUT_GENE_NAME)) {
			pData.setInputGeneData(
					// TODO: Need to have better typecase and nullcheck handling
					// on session.get
					retrieveCorrelationData.retrieveCorrelationData(
							(Gene) session.getAttribute(CUSTOM_GENE_SESSION_NAME), selectedIndices));
		} else {
			pData.setInputGeneData(
					retrieveCorrelationData.retrieveCorrelationData(body.getInputGene(), selectedIndices));
		}
		pData.setTargetGeneData(retrieveCorrelationData.retrieveCorrelationData(body.getTargetGene(), selectedIndices));
		return ResponseEntity.ok(pData);
	}

	private List<String> retrieveSelectedExpressionLabels(Map<String, Boolean> selectedExpressionStages) {
		List<String> allExpressionStages = retrieveExpressionStages.getDmelanogasterExpressionStages();
		List<Integer> selectedExpressionIndices = ExpressionStageProcessor
				.selectedExpressionStageIndices(selectedExpressionStages, allExpressionStages);
		List<String> selectedExpressionStageLabels = ExpressionStageProcessor
				.selectedExpressionStageLabels(selectedExpressionIndices, allExpressionStages);
		return selectedExpressionStageLabels;
	}

	private Gene retrieveCustomGene(Map<String, Integer> customExpressionMap) {
		Gene customGene = null;
		boolean customExpressionFormPosted = false;
		/*
		 * TODO: Need to handle scenarios where all 0s are sent in form,
		 * although I am sure this would break the correlation analysis (need to
		 * restrict identical value submission)
		 * 
		 * Must find new way to check if customExpressionForm is posted.
		 */
		for (Map.Entry<String, Integer> entry : customExpressionMap.entrySet()) {
			if (entry.getValue() != 0d) {
				customExpressionFormPosted = true;
				break;
			}
		}
		if (customExpressionFormPosted) {
			List<String> allExpressionStages = retrieveExpressionStages.getDmelanogasterExpressionStages();
			// CREATE RNAEXP
			// TODO: RNAEXP is an integer array, thus the form needs to be from
			// 1 to 100 instead. For now, will just multiple by 100
			int[] rnaExpData = new int[allExpressionStages.size()];
			// TODO: SET NO GAURANTEE OF SORTNESS, need to do string mapping
			// between expressionStageMap and customExpressionMap
			for (int i = 0; i < allExpressionStages.size(); i++) {
				rnaExpData[i] = customExpressionMap.get(allExpressionStages.get(i));
			}

			// CREATE GENE
			customGene = new Gene();
			customGene.setGeneName("INPUT GENE");
			customGene.setDbIdentifier("INPUT GENE");
			customGene.setSecondaryIdentifier("INPUT GENE");
			customGene.setRnaExpData(rnaExpData);
		}
		return customGene;
	}

	private List<String> retrieveGeneOfInterestList(String genesOfInterest) {
		// TODO: Input and security validation
		List<String> geneOfInterestList = new ArrayList<String>(
				Arrays.asList(StringUtils.delimitedListToStringArray(genesOfInterest, ",")));

		/*
		 * Trims and validates genesOfInterest. TODO: Make Validator class for
		 * this TODO: Move this logic to different class (TO to regular object)
		 */
		final ListIterator<String> li = geneOfInterestList.listIterator();
		while (li.hasNext()) {
			final String element = li.next();
			li.set(StringUtils.trimWhitespace(element));
		}
		return geneOfInterestList;

	}

}
