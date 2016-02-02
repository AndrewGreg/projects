package edu.ben.template.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	/**
	 * Access to the Homepage.
	 * @param model is being passed in
	 * @return the index page.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	
	@RequestMapping(value = "/alumniDirectory", method = RequestMethod.GET)
//	public String directory(Model model,  @RequestParam("first") String firstName,  @RequestParam("last") String lastName, 
//			@RequestParam("year") String yearGraduated, @RequestParam("degree") String degree) {
		public String directory(Model model) {
		return "alumniDirectory";
	}
	

	/**
	 * Accesses the user profile page. Dynamically grabs information depending
	 * who is logged in such as student, alumni, and faculty.
	 * 
	 * @param model
	 *            is being passed in.
	 * @return the profile page that belongs to the user.
	 */
	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
//	public String userProfile(Model model, @RequestParam("name") String name, @RequestParam("bio") String biography,
//			@RequestParam("occupation") String occupation, @RequestParam("graduation") String graduation,
//			@RequestParam("workInterest") String workInterest, @RequestParam("experience") String experience) {
	public String userProfile(Model model){

		// try {
		//
		// ArrayList<User> user = new ArrayList<User>();
		// users = getJdbcTypeDao().findAll();
		//
		// model.addAttribute("users", users);
		//
		// } catch (Exception e) {
		// }
		//

		return "userProfile";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/somethingSecret", method = RequestMethod.GET)
	public String viewSecret(Model model) {
		return "secret";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userHome(Model model) {
		return "index";
	}
}
