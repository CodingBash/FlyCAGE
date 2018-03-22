package edu.ilstu.biology.flytranscriptionwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FeedbackController {
	
	@RequestMapping(value="/feedback", method=RequestMethod.GET)
	public String feedback(){
		return "feedback";
	}
}
