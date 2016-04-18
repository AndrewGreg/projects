package edu.ben.template.controller;

import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.ben.template.dao.DaoKeeper;
import edu.ben.template.model.User;

/**
 * base location for controllers
 * 
 */
public abstract class BaseController extends DaoKeeper {

	/**
	 * Retrieves the current user from the principal object
	 * 
	 * @return the currentUser logged in.
	 */
	@ModelAttribute("currentUser")
	public User getCurrentUser() {
		return getUserFromPrincipal();
	}

	/**
	 * Retrieves the current user from the principal object
	 * 
	 * @param principal
	 * @return the logged in user.
	 */
	public User getUserFromPrincipal(Object principal) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;

		String userName = user.getUsername();
		User loggedUser = getUserDao().getByEmail(userName);
		return loggedUser;
	}

	/**
	 * Retrieves the current user from the principal object
	 * 
	 * @return
	 */
	public User getUserFromPrincipal() {
		if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null
				&& !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return getUserFromPrincipal(principal);
		}
		return null;
	}

	/**
	 * helper to grab the session
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
			HttpServletResponse response) {
		return "/Alumni-Tracker/customError.jsp";
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String handleAccessDeniedException(AccessDeniedException e, HttpServletResponse response) {
		return "errors/accessDenied";
	}
	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView Exception(Exception ex,HttpServlet req) {
//		ModelAndView model = new ModelAndView("/Alumni-Tracker/customError.jsp");
//
//		
//		model.addObject("exception", ex);
//		model.addObject("url", ((HttpServletRequest) req).getRequestURL());
//	    model.setViewName("error");
//		return model;
//
//	}
	
	@ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
            ModelAndView mav = new ModelAndView("/Alumni-Tracker/customError.jsp");

        mav.addObject("datetime", new Date());
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
