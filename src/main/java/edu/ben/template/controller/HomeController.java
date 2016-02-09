package edu.ben.template.controller;

import java.util.ArrayList;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ben.template.dao.JobPostingDao;
import edu.ben.template.model.JobPosting;

@Controller
public class HomeController {

	/**
	 * Access to the Homepage.
	 * 
	 * @param model
	 *            is being passed in
	 * @return the index page.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	/**
	 * Access to the registration page.
	 * 
	 * @param model
	 *            is being passed in
	 * @return the registration page.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registration(Model model) {
		return "register";
	}

	/**
	 * Form processing for the registration page.
	 * 
	 * @param registration
	 *            information passed in
	 * @return to be determined.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registrationPost(Model model, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("benEmail") String benEmail,
			@RequestParam("personalEmail") String personalEmail, @RequestParam("gradYear") String gradYear,
			@RequestParam("occupation") String occupation, @RequestParam("title") String title,
			@RequestParam("suffix") String suffix, @RequestParam("password") String password,
			@RequestParam("passConfirm") String passConfirm) {
		
		//TODO Process the form information and create a user

		return "register";
	}

	/**
	 * Access to the job postings page.
	 * 
	 * @param model
	 *            is being passed in
	 * @return the job postings page.
	 */
	@RequestMapping(value = "/jobPostings", method = RequestMethod.GET)
	public String jobPostings(Model model) {

		// TODO Remove the permit all access from the security config
		// TODO Remove the static well example on the jsp

		// NullPointerException on job posting dao
		// JobPostingDao dao = new JobPostingDao();
		// ArrayList<JobPosting> jobPostings = dao.findAll();

		ArrayList<JobPosting> jobPostings = new ArrayList<JobPosting>();
		JobPosting test = new JobPosting("Test", "Test posting for the controller", "SomeCompany");
		jobPostings.add(test);

		model.addAttribute("jobPostings", jobPostings);

		return "jobPostings";
	}

	@RequestMapping(value = "/alumniDirectory", method = RequestMethod.GET)
	// public String directory(Model model, @RequestParam("first") String
	// firstName, @RequestParam("last") String lastName,
	// @RequestParam("year") String yearGraduated, @RequestParam("degree")
	// String degree) {
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
	// public String userProfile(Model model, @RequestParam("name") String name,
	// @RequestParam("bio") String biography,
	// @RequestParam("occupation") String occupation,
	// @RequestParam("graduation") String graduation,
	// @RequestParam("workInterest") String workInterest,
	// @RequestParam("experience") String experience) {
	public String userProfile(Model model) {

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
