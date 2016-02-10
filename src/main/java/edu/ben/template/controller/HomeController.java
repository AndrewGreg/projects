package edu.ben.template.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ben.template.dao.JobPostingDao;

import edu.ben.template.dao.UserDao;
import edu.ben.template.model.JobPosting;
import edu.ben.template.model.User;

@Controller
public class HomeController extends BaseController{

	

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
			@RequestParam("standing") String standing, @RequestParam("occupation") String occupation,
			@RequestParam("title") String title, @RequestParam("suffix") String suffix,
			@RequestParam("password") String password, @RequestParam("passConfirm") String passConfirm) {

		boolean validatePerEmail = personalEmail != null && !personalEmail.equals("") ? true : false;

		int graduationYear = gradYear != null && !gradYear.equals("") && gradYear.matches("[0-9]{4}")
				? Integer.parseInt(gradYear) : (gradYear == null || gradYear.equals("") ? 1 : -1);
		int role = standing.equals("1") || standing.equals("2") || standing.equals("3") ? Integer.parseInt(standing)
				: -1;

		if ((firstName != null && firstName.matches(".{2,}")) && (lastName != null && lastName.matches(".{2,}"))
				&& (benEmail != null && benEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@ben.edu"))
				&& ((validatePerEmail
						&& personalEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@[a-zA-Z_0-9]+[.][a-zA-Z_0-9]{2,4}")) || !validatePerEmail)
				&& (graduationYear != -1) && (role != -1) && ((password != null && password.matches(".{2,}")
						&& passConfirm != null && passConfirm.matches(".{2,}")) && password.equals(passConfirm))) {

			User register = new User();

			register.setEmail(benEmail);
			register.setFirstName(firstName);
			register.setLastName(lastName);

			if (graduationYear != 0) {
				register.setGraduationYear(graduationYear);
			}

			if (occupation == null || occupation.equals("")) {
				register.setOccupation(null);
			} else {
				register.setOccupation(occupation);
			}

			register.setRole(role);
			register.setPersonalEmail(personalEmail);

			if (title == null || title.equals("")) {
				register.setTitle(null);
			} else {
				register.setTitle(title);
			}

			if (suffix == null || suffix.equals("")) {
				register.setSuffix(null);
			} else {
				register.setSuffix(suffix);
			}
			
			//TODO Hash the password before saving to the user
			register.setPassword(password);
			
			//TODO Make sure database works
			UserDao dao = new UserDao();
			dao.addUser(register);

			return "index";

		} else {
			HashMap<String, String> errors = new HashMap<String, String>();

			if (firstName == null || !firstName.matches(".{2,}")) {
				errors.put("firstName", "Error in the input for first name.");
			}

			if (lastName == null || !lastName.matches(".{2,}")) {
				errors.put("lastName", "Error in the input for last name.");
			}

			if (benEmail == null || !benEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@ben.edu")) {
				errors.put("benEmail", "Error in the input for your Benedictine email.");
			}

			
			if (validatePerEmail && !personalEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@[a-zA-Z_0-9]+[.][a-zA-Z_0-9]{2,4}")) {
				errors.put("personalEmail", "Error in the input for your personal email.");
			}

			if (graduationYear == -1) {
				errors.put("gradYear", "Error in the input for the graduation year.");
			}

			if (role == -1) {
				errors.put("standing", "You must choose a valid standing.");
			}

			// TODO Add validation for title and suffix
			// find the regex to invalidate two special characters together

			if ((password == null || !password.matches(".{2,}") || passConfirm == null || !passConfirm.matches(".{2,}"))
					|| !password.equals(passConfirm)) {
				errors.put("passwords", "Passwords either do not match or are too short.");
			}

			model.addAttribute("errors", errors);
			
			return "register";
		}
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

		ArrayList<JobPosting> jobPostings = new ArrayList<JobPosting>();
		JobPosting test = new JobPosting("Test", "Test posting for the controller", "SomeCompany");
		jobPostings.add(test);

		model.addAttribute("jobPostings", jobPostings);

		// NullPointerException on job posting dao
		// JobPostingDao dao = new JobPostingDao();
		// ArrayList<JobPosting> jobPostings = dao.findAll();

		try {

			ArrayList<JobPosting> job = new ArrayList<JobPosting>();
			job = getJobPostingDao().findAll();

			model.addAttribute("job", job);

		} catch (Exception e) {

		}

		return "jobPostings";
	}

	/**
	 * Access to the Faculty Profile page.
	 * 
	 * @param model
	 *            is being passed in
	 * @return the faculty page.
	 */
	@RequestMapping(value = "/facultyProfile", method = RequestMethod.GET)
	public String faculty(Model model) {

		return "facultyProfile";
	}

	/**
	 * Displays all the alumni users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the alumni Directory page.
	 */
	@RequestMapping(value = "/alumniDirectory", method = RequestMethod.GET)
	public String directory(Model model) {

		try {

			ArrayList<User> alumni = new ArrayList<User>();
			alumni = getUserDao().findAll();
			
			model.addAttribute("alumni", alumni);

		} catch (Exception e) {
			e.printStackTrace();
		}
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
	@RequestMapping(value = "/userProfile{userId}", method = RequestMethod.GET)
	public String userProfile(Model model, @PathVariable("userId") Integer userId) {
		ArrayList<User> user = new ArrayList<User>();

		model.addAttribute("user", user);

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
