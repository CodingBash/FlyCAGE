package edu.ilstu.biology.flytranscriptionwebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneXml;

@Controller
public class OutputController {

	@Autowired
	@Qualifier("genomeData")
	private List<Gene> genomeData;

	@RequestMapping(value = "/output", method = RequestMethod.GET)
	public ModelAndView processOutput(@ModelAttribute("geneForm") GeneForm geneForm) {
		ModelAndView mav = new ModelAndView();
		GeneXml geneObject = new GeneXml();
		geneObject.setGeneName(geneForm.getGeneName());
		mav.addObject("gene", geneObject);
		mav.addObject("geneForm", geneForm);
		mav.setViewName("output");
		return mav;
	}
}
