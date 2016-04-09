package edu.ben.template.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.ben.template.dao.DaoKeeper;
import edu.ben.template.dao.UserDao;

/**
 * sets the _user and _account request variables for ease of use
 */
public class UserInterceptor extends DaoKeeper implements HandlerInterceptor {
	@Resource(name="userDao")
	private static UserDao userDao;

	// logging
	final static Logger _log = LoggerFactory.getLogger(UserInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/* TODO Need to write this... */
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		/* TODO Need to write this... */
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		/* TODO Nothing really required here */
	}

}