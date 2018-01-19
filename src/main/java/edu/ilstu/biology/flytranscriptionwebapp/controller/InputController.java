package edu.ilstu.biology.flytranscriptionwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;
import edu.ilstu.biology.flytranscriptionwebapp.processor.ExpressionStageOptionsGenerator;

@Controller
public class InputController {

	@Autowired
	private ExpressionStageOptionsGenerator expressionStageOptionsGenerator;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showInputPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("geneForm", new GeneForm());
		mav.addObject("expressionStageOptions", expressionStageOptionsGenerator.generateExpressionStageOptions());
		mav.setViewName("input");
		return mav;

	}

	/*
	 * TODO: GeneForm Validation
	 * TODO: Example: Ensure at least two expression stages are selected
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String processInput(GeneForm geneForm, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("geneForm", geneForm);
		return "redirect:/output";
	}
}
