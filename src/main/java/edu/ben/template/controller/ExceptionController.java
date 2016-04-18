package edu.ben.template.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


public class ExceptionController {


	@ExceptionHandler(Exception.class)
	public ModelAndView noHandlerFoundException(Exception ex) {

		ModelAndView model = new ModelAndView("/Alumni-Tracker/customError.jsp");
		model.addObject("exception", ex);
		return model;

	}
	

}
