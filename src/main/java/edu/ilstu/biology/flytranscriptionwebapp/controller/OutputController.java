package edu.ilstu.biology.flytranscriptionwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneXml;

@Controller
public class OutputController {

	@RequestMapping(value="/output", method=RequestMethod.GET)
	public ModelAndView processOutput(@RequestParam("gene") String gene){
		ModelAndView mav = new ModelAndView();
		GeneXml geneObject = new GeneXml();
		geneObject.setGeneName(gene);
		mav.addObject("gene", geneObject);
		mav.setViewName("output");
		return mav;
	}
}
