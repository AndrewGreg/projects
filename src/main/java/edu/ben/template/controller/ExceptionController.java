package edu.ben.template.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	@RequestMapping("e")
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("customError");
		return mav;

	}

	@RequestMapping("notAuthorized")
	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView notAuthorized(HttpServletRequest request, AccessDeniedException e) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("AccessDeniedException", e);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName("noAccessError");
		return mav;

	}

}
