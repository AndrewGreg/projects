package edu.ben.template.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ben.template.model.LoginCredentials;
import edu.ben.template.model.User;
import edu.ben.template.dao.UserDao;

@Controller
public class LoginController extends BaseController {
	final Logger _log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) {
		
		User u = new User();
		if(getUserDao().findByEmail(email) != null){
			u = getUserDao().findByEmail(email);
			
			if(!password.equals(u.getPassword())){
				model.addAttribute("invalid", "invalid");
				return "login";
			}
			
		} else {
			model.addAttribute("invalid", "invalid");
			return "login";
		}
		
		_log.debug("Entering login");
		model.addAttribute("invalid", null);
		model.addAttribute("user", u);
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		_log.debug("Entering login");
		model.addAttribute("credentials", new LoginCredentials());
		model.addAttribute("user", new User());
		return "login";
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
		return "login";
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) throws SQLException {

		model.addAttribute("logout", "logout");
		model.addAttribute("user", null);
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String edit(Model model) throws SQLException {

		model.addAttribute("logout", "logout");
		model.addAttribute("user", null);
		return "index";
	}
}