package edu.ilstu.biology.flytranscriptionwebapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;
import edu.ilstu.biology.flytranscriptionwebapp.model.PairwiseCorrelationDataAjaxRequestBody;
import edu.ilstu.biology.flytranscriptionwebapp.model.PairwiseGeneCorrelationData;
import edu.ilstu.biology.flytranscriptionwebapp.processor.ExpressionStageOptionsGenerator;
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

	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public ModelAndView processOutput(@ModelAttribute("geneForm") GeneForm geneForm) {
		ModelAndView mav = new ModelAndView();
		List<String> allExpressionStages = retrieveExpressionStages.getDmelanogasterExpressionStages();

		List<Integer> selectedExpressionIndices = selectedExpressionStageIndices(geneForm.getExpressionStages(),
				allExpressionStages);

		// TODO: Input and security validation
		List<String> geneOfInterestList = new ArrayList<String>(
				Arrays.asList(StringUtils.delimitedListToStringArray(geneForm.getGenesOfInterest(), ",")));

		/*
		 * Trims and validates genesOfInterest. TODO: Make Validator class for
		 * this TODO: Move this logic to different class (TO to regular object)
		 */
		final ListIterator<String> li = geneOfInterestList.listIterator();
		while (li.hasNext()) {
			final String element = li.next();
			li.set(StringUtils.trimWhitespace(element));
		}

		FinalResponseCorrelationResult result = null;
		try {
			result = correlationAnalysis.retrieveMrnaCorrelationResults(geneForm.getInputIdentifier(),
					selectedExpressionIndices, geneOfInterestList, geneForm.getGeneResultCount());
		} catch (InvalidGeneException ige) {
			// TODO: Consider a redirect to "GET /" w/ redirectattributes (to switch the URL)
			mav.addObject("exception", ige);
			ExpressionStageOptions expressionStageOptions = expressionStageOptionsGenerator
					.generateExpressionStageOptions(); // TODO: Duplicate
														// expression
														// generation. Remove
														// and use only one for
														// both results
			mav.addObject("expressionStageOptions", expressionStageOptions);
			mav.setViewName("input"); 
			return mav;
		}
		mav.addObject("result", result);
		ExpressionStageOptions expressionStageOptions = expressionStageOptionsGenerator
				.generateExpressionStageOptions();
		mav.addObject("expressionStageOptions", expressionStageOptions);
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
			@RequestBody PairwiseCorrelationDataAjaxRequestBody body) {
		PairwiseGeneCorrelationData pData = new PairwiseGeneCorrelationData();
		List<Integer> selectedIndices = selectedExpressionStageIndices(body.getSelectedExpressionStages(),
				retrieveExpressionStages.getDmelanogasterExpressionStages());
		pData.setInputGeneData(retrieveCorrelationData.retrieveCorrelationData(body.getInputGene(), selectedIndices));
		pData.setTargetGeneData(retrieveCorrelationData.retrieveCorrelationData(body.getTargetGene(), selectedIndices));
		return ResponseEntity.ok(pData);
	}

	// TODO: Move to its own processor
	public List<Integer> selectedExpressionStageIndices(Map<String, Boolean> selectedExpressionStages,
			List<String> allExpressionStages) {
		List<Integer> selectedExpressionIndices = new LinkedList<Integer>();
		for (Map.Entry<String, Boolean> entry : selectedExpressionStages.entrySet()) {
			if (entry.getValue() != null && entry.getValue() == Boolean.TRUE) {
				selectedExpressionIndices.add(allExpressionStages.indexOf(entry.getKey()));
			}
		}
		return selectedExpressionIndices;
	}

}
