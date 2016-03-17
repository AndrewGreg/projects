package edu.ben.template.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ben.template.model.LoginCredentials;
import edu.ben.template.model.User;

@Controller
public class LoginController {
	final Logger _log = LoggerFactory.getLogger(LoginController.class);

	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		_log.debug("Entering login");
		model.addAttribute("credentials", new LoginCredentials());
		model.addAttribute("user", new User());
		return "/";
	}

	/**
	 * This is here as a workaround to display the login failed message. there
	 * is probably a better way to do this in the spring security world
	 */
	@RequestMapping(value = "/loginFailure", method = RequestMethod.GET)
	public String loginFailed(Model model) {
		// add an error message
		model.addAttribute("credentials", new LoginCredentials());
		model.addAttribute("user", new User());
		model.addAttribute("loginAttempt", "failure");
		return "indexTemplate";
	}
	
}