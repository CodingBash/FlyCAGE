package edu.ilstu.biology.flytranscriptionwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneCorrelatedResult;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;
import edu.ilstu.biology.flytranscriptionwebapp.processor.GenomicCorrelationAnalysis;

@Controller
public class OutputController {

	@Autowired
	private GenomicCorrelationAnalysis correlationAnalysis;
	
	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public ModelAndView processOutput(@ModelAttribute("geneForm") GeneForm geneForm) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("output");
		List<GeneCorrelatedResult> results = correlationAnalysis.retrieveMrnaCorrelationResults(geneForm.getGeneName());
		System.out.println("break");
		
		return mav;
	}
}
