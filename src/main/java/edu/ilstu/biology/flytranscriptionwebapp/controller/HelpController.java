package edu.ilstu.biology.flytranscriptionwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelpController {

	
	@RequestMapping(value="/help", method=RequestMethod.GET)
	public ModelAndView help(){
		ModelAndView mav = new ModelAndView("help");
		
		// TODO: Add models
		
		return mav;
	}
}
