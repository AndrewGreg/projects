package edu.ben.template.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSeparatorUI;


import org.springframework.context.annotation.Scope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import edu.ben.template.dao.FileUploadDao;
import edu.ben.template.model.Event;
import edu.ben.template.model.JobPosting;
import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;

@Controller
public class HomeController extends BaseController{
//	 @Autowired
//	 private FileUploadDao fileUploadDao;
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
						&& personalEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@[a-zA-Z_0-9]+[.][a-zA-Z_0-9]{2,4}"))
						|| !validatePerEmail)
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

			// TODO Hash the password before saving to the user
			register.setPassword(password);

			getUserDao().addUser(register);

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

			if (validatePerEmail
					&& !personalEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@[a-zA-Z_0-9]+[.][a-zA-Z_0-9]{2,4}")) {
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

	@RequestMapping(value = "/editAccount", method = RequestMethod.GET)
	public String editAccount(
			Model model/*
						 * , @RequestParam("firstName") String firstName,
						 * 
						 * @RequestParam("lastName") String
						 * lastName, @RequestParam("benEmail") String benEmail,
						 * 
						 * @RequestParam("personalEmail") String
						 * personalEmail, @RequestParam("gradYear") String
						 * gradYear,
						 * 
						 * @RequestParam("standing") String
						 * standing, @RequestParam("occupation") String
						 * occupation,
						 * 
						 * @RequestParam("title") String
						 * title, @RequestParam("suffix") String suffix,
						 * 
						 * @RequestParam("password") String
						 * password, @RequestParam("passConfirm") String
						 * passConfirm
						 */) {

		// GET USER FROM SESSION

		// DUMMY User for now

		User u = getUserDao().getObjectById(1);

		System.out.println(u.getFirstName() + " " + u.getLastName());

		// CHECK Profile Role

		// For dropdown of major -> for loop to mark if selected

		// JSP has a password and confirm password

		// Confirm EDIT
		// return to user profile

		// Can't confirm EDIT
		// return to user profile but incomplete

		return "userProfile{userId}";// TODO CHECK THIS URL

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

		try {

			ArrayList<JobPosting> job = getJobPostingDao().findAll();

			model.addAttribute("jobPostings", job);

		} catch (Exception e) {
			e.printStackTrace();
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

		// Return all Students
		try {
			ArrayList<User> student = new ArrayList<User>();
			student = getUserDao().findAllStudents();

			model.addAttribute("student", student);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return all Alumni
		try {
			ArrayList<User> alumni = new ArrayList<User>();
			alumni = getUserDao().findAllAlumni();

			model.addAttribute("alumni", alumni);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "facultyProfile";
	}
	
//	@RequestMapping(value = "/facultyProfile", method = RequestMethod.POST)
//	public String facultyUpload(Model model, HttpServletRequest request,
//            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
//		
//		if (fileUpload != null && fileUpload.length > 0) {
//            for (CommonsMultipartFile aFile : fileUpload){
//                  
//                //System.out.println("Saving file: " + aFile.getOriginalFilename());
//                 
//                UploadFile uploadFile = new UploadFile();
//                uploadFile.setFileName(aFile.getOriginalFilename());
//                uploadFile.setData(aFile.getBytes());
//                fileUploadDao.save(uploadFile);               
//            }
//        }
//
//		return "facultyProfile";
//	}

	/**
	 * Displays all the alumni users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the alumni Directory page.
	 */
	@RequestMapping(value = "/alumniDirectory", method = RequestMethod.GET)
	public String directory(@RequestParam(required = false) Integer page, Model model) {

		try {

			ArrayList<User> alumni = new ArrayList<User>();
			alumni = getUserDao().findAll();

			for (User users : alumni) {
				users.setMajor(getMajorDao().findMajorByUser(users));
				users.setConcentration(getMajorDao().findConcentrationByUser(users));
				users.setMinor(getMajorDao().findMinorByUser(users));

			}

			sortUsers(alumni);
			if (page == null) {
				page = 0;
			}
			ArrayList<User> users = new ArrayList<User>();
			for (int i = page * 15; i < page * 15 + 15; i++) {

				if (i < alumni.size()) {

					users.add(alumni.get(i));
				}
			}
			model.addAttribute("alumni", users);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "alumniDirectory";

	}

	/**
	 * Sort Method to compare the first name of every user.
	 * 
	 * @param user
	 *            is being passed in.
	 */
	public void sortUsers(ArrayList<User> user) {

		Collections.sort(user, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getFirstName().compareTo(o2.getFirstName());
			}
		});
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
	
//	@RequestMapping(value = "/userProfile", method = RequestMethod.POST)
//	public String userProfileUpload(Model model, HttpServletRequest request,
//            @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
//		
//		if (fileUpload != null && fileUpload.length > 0) {
//            for (CommonsMultipartFile aFile : fileUpload){
//                  
//                //System.out.println("Saving file: " + aFile.getOriginalFilename());
//                 
//                UploadFile uploadFile = new UploadFile();
//                uploadFile.setFileName(aFile.getOriginalFilename());
//                uploadFile.setData(aFile.getBytes());
//                fileUploadDao.save(uploadFile);               
//            }
//        }
//
//		return "userProfile";
//	}

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
