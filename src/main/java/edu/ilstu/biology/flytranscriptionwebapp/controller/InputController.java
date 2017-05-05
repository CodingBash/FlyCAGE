package edu.ilstu.biology.flytranscriptionwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;

@Controller
public class InputController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView showInputPage(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("geneForm", new GeneForm());
		mav.setViewName("input");
		return mav;
		
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String processInput(GeneForm geneForm, RedirectAttributes redirectAttributes){
		redirectAttributes.addFlashAttribute("geneForm", geneForm);
		return "redirect:/output";
	}
}
