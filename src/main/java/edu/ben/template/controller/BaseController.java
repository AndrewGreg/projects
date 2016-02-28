package edu.ben.template.controller;

import java.util.ArrayList;

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

import edu.ben.template.dao.DaoKeeper;
import edu.ben.template.model.JobPosting;
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
		// get the security principal
		return getUserFromPrincipal();
	}

	/**
	 * Retrieves the current user from the principal object
	 * 
	 * @param principal
	 * @return
	 */
	public User getUserFromPrincipal(Object principal) {
		System.out.println(principal);
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;

		String userName = user.getUsername();

		System.out.println(userName);
		User loggedUser = getUserDao().findByEmail(userName);

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

	// public long getCurrentUserId() {
	// User u = (User)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// return u.getId();
	// }
}
