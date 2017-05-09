package edu.ilstu.biology.flytranscriptionwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.biology.flytranscriptionwebapp.model.FinalResponseCorrelationResult;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;
import edu.ilstu.biology.flytranscriptionwebapp.model.PairwiseCorrelationDataAjaxRequestBody;
import edu.ilstu.biology.flytranscriptionwebapp.model.PairwiseGeneCorrelationData;
import edu.ilstu.biology.flytranscriptionwebapp.processor.GenomicCorrelationAnalysis;
import edu.ilstu.biology.flytranscriptionwebapp.processor.RetrieveCorrelationData;

@Controller
public class OutputController {

	@Autowired
	private GenomicCorrelationAnalysis correlationAnalysis;

	@Autowired
	private RetrieveCorrelationData retrieveCorrelationData;

	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public ModelAndView processOutput(@ModelAttribute("geneForm") GeneForm geneForm) {
		ModelAndView mav = new ModelAndView("output");

		FinalResponseCorrelationResult result = correlationAnalysis
				.retrieveMrnaCorrelationResults(geneForm.getInputIdentifier());
		mav.addObject("result", result);
		// TODO: Eventually map the geneForm to a better Gene object
		mav.addObject("geneForm", geneForm);
		return mav;
	}

	/*
	 * TODO: Only accept and send targetgene data. Front end should have access
	 * of inputgene data for optimization
	 */
	@PostMapping("/pairwise-correlation-data")
	public ResponseEntity<PairwiseGeneCorrelationData> getSearchResultViaAjax(
			@RequestBody PairwiseCorrelationDataAjaxRequestBody body) {
		PairwiseGeneCorrelationData pData = new PairwiseGeneCorrelationData();
		pData.setInputGeneData(retrieveCorrelationData.retrieveCorrelationData(body.getInputGene()));
		pData.setTargetGeneData(retrieveCorrelationData.retrieveCorrelationData(body.getTargetGene()));
		return ResponseEntity.ok(pData);
	}

}
