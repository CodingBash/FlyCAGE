package edu.ilstu.biology.flytranscriptionwebapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import edu.ilstu.biology.flytranscriptionwebapp.validation.InvalidGeneException;

@ControllerAdvice
public class ExceptionController {

	/**
	 * @deprecated needs access to {@link GeneForm} object. Therefore, I need to
	 *             go the more manual route and catch this RuntimeException in
	 *             the controller methods
	 * @param req
	 * @param ex
	 * @return
	 */
	@Deprecated
	@ExceptionHandler(InvalidGeneException.class)
	public ModelAndView handleInvalidGeneException(HttpServletRequest req, Exception ex) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("input"); // TODO: Create view
		return mav;
	}
}
