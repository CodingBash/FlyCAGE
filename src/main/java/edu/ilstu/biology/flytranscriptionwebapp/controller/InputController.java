package edu.ilstu.biology.flytranscriptionwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;

@Controller
public class InputController {

	@RequestMapping(value="/", method=RequestMethod.POST)
	public String processInput(GeneForm form, RedirectAttributes model){
		// TODO: Logic
		return "redirect:/output?gene={gene}";
	}
}
