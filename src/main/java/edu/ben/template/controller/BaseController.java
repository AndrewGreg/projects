package edu.ben.template.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import edu.ben.template.dao.DaoKeeper;
import edu.ben.template.interceptor.UserInterceptor;
import edu.ben.template.model.User;

/**
 * base location for controllers
 * 
 */
public abstract class BaseController extends DaoKeeper {

	/**
	 * Retrieves the current user from the principal object
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		// get the security principal
		return UserInterceptor.getUserFromPrincipal();
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
	public void handleMissingServletRequestParameterException(MissingServletRequestParameterException e,
			HttpServletResponse response) {
		/* TODO THIS SHOULD BE LOGGED SOMEWHERE */
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String handleAccessDeniedException(AccessDeniedException e, HttpServletResponse response) {
		/* TODO THIS SHOULD BE LOGGED SOMEWHERE */
		return "errors/accessDenied";
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGeneralException(Exception e, HttpServletResponse response) {
		/* TODO THIS SHOULD BE LOGGED SOMEWHERE */
		return "errors/error500";
	}

	/**
	 * needed to fix a bug in json request
	 * https://jira.springsource.org/browse/SPR-9310
	 * 
	 * @param e
	 * @param response
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	public String handleBindException(BindException e, HttpServletResponse response) {
		/* TODO THIS SHOULD BE LOGGED SOMEWHERE */
		return "errors/error500";
	}

	public long getCurrentUserId() {
		User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return u.getId();
	}
}
