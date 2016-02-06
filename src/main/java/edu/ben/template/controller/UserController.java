package edu.ben.template.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.ben.template.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController extends BaseController {

	@PreAuthorize("isFullyAuthenticated()")
	@RequestMapping(value = "/displayAccount", method = RequestMethod.GET)
	public String displayAccount(Model model) {
		long userId = getCurrentUserId();

		User user = getUserDao().getObjectById((int) userId);

		model.addAttribute("user", user);
		return "account/view";
	}
}
