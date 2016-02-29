package edu.ben.template.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import edu.ben.template.model.Event;
import edu.ben.template.model.JobPosting;
import edu.ben.template.model.Major;
import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;
import edu.ben.template.model.Validator;

@Controller
public class HomeController extends BaseController {

	// Allows the password to be Hashed.
	@Resource(name = "passwordEncoder")
	private PasswordEncoder pwEncoder;

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
	 * Index method.
	 * 
	 * @param model
	 *            being passed.
	 * @return to the homepage of Alumni Tracker.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";

	}

	/**
	 * Access to the Job Creation page.
	 * 
	 * @param model
	 *            is being passed in
	 * @return the job creation page.
	 */
	@RequestMapping(value = "/createJobPosting", method = RequestMethod.GET)
	public String jobCreation(Model model) {
		return "createJobPosting";
	}

	/**
	 * Form processing of the job posting creation page.
	 * 
	 * @param model
	 *            of passing
	 * @param name
	 *            of the Job.
	 * @param company
	 *            that belongs to the Job.
	 * @param description
	 *            of the Job.
	 * @return the Job Posting page to view all the jobs.
	 */
	@RequestMapping(value = "/createJobPosting", method = RequestMethod.POST)
	public String createJobPostingPost(Model model, @RequestParam("name") String name,
			@RequestParam("company") String company, @RequestParam("description") String description) {

		if (name != null && name.matches(".{2,}") && company != null && company.matches(".{2,}") && description != null
				&& description.matches(".{2,}")) {

			// TODO Find out how to get the logged in user to add to the
			// jobPosting object
			User u = getCurrentUser();

			JobPosting job = new JobPosting(name, description, company, u);

			try {

				getJobPostingDao().addJobPosting(job);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Job was created");
			return "redirect:/jobPostings";

		} else {

			HashMap<String, String> errors = new HashMap<String, String>();

			if (name == null || !name.matches(".{2,}")) {
				errors.put("name", "Error in the input for the job name.");
			}

			if (company == null || !company.matches(".{2,}")) {
				errors.put("company", "Error in the input for the job's company.");
			}

			if (description == null || !description.matches(".{2,}")) {
				errors.put("description", "Error in the input for the job description.");
			}

			model.addAttribute("errors", errors);

			return "createJobPosting";
		}
	}

	/**
	 * Method to request the Get for creating an event.
	 * 
	 * @param model
	 *            being passed.
	 * @return createEvent page
	 */
	@RequestMapping(value = "/createEvent", method = RequestMethod.GET)
	public String createEvent(Model model) {
		return "createEvent";
	}

	/**
	 * Form processing the event creation page.
	 * 
	 * @param model
	 *            being passed.
	 * @param name
	 *            of the event.
	 * @param dateStr
	 *            date of the event.
	 * @param description
	 *            of the event.
	 * @return to the event posting page to view the created on and the rest of
	 *         them.
	 */
	@RequestMapping(value = "/createEvent", method = RequestMethod.POST)
	public String createEventPost(Model model, @RequestParam("name") String name, @RequestParam("date") String dateStr,
			@RequestParam("description") String description) {

		if (name != null && name.matches(".{2,}") && description != null && description.matches(".{2,}")
				&& dateStr != null && dateStr.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {

			String[] datePart = dateStr.split("/");

			// Subtracted 1900 from year and 1 from month to offset the
			// deprecated constructor
			Date eventDate = new Date(Integer.parseInt(datePart[2]) - 1900, Integer.parseInt(datePart[0]) - 1,
					Integer.parseInt(datePart[1]));
			Date currentDate = new Date(System.currentTimeMillis());

			User u = getCurrentUser();

			Event createEvent = new Event();

			createEvent.setName(name);
			createEvent.setDescription(description);
			createEvent.setDate(eventDate);
			createEvent.setPoster(u);

			if (eventDate.compareTo(currentDate) < 0) {

				HashMap<String, String> errors = new HashMap<String, String>();

				errors.put("date", "Error. The event's date must be after the current date.");

				model.addAttribute("errors", errors);

				return "createEvents";
			}

			System.out.println("Event was created.");

			try {
				getEventDao().addEvent(createEvent);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "events";

		} else {

			HashMap<String, String> errors = new HashMap<String, String>();

			if (name == null || !name.matches(".{2,}")) {
				errors.put("name", "Error in the input for the event name.");
			}

			if (description == null || !description.matches(".{2,}")) {
				errors.put("description", "Error in the input for the event description.");
			}

			if (dateStr == null || !dateStr.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
				errors.put("date", "Error in the input for the event's date.");
			}

			model.addAttribute("errors", errors);

		}

		return "events";

	}

	/**
	 * Displays all events.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the page returning all the events being posted.
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public String eventPostings(Model model) {

		try {
			ArrayList<Event> events = new ArrayList<Event>();
			events = getEventDao().findAll();

			model.addAttribute("events", events);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "events";
	}

	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public String eventDisplay(Model model, @PathVariable Long id) {

		try {

			Event currentEvent = getEventDao().getObjectById(id);
			model.addAttribute("currentEvent", currentEvent);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "eventDisplay";

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

		try {

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
				register.setPersonalEmail(personalEmail);
				register.setFirstName(firstName);
				register.setLastName(lastName);

				// FIX THE SALT
				register.setSalt(password);

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

				register.setPassword(pwEncoder.encode(password));

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

				if ((password == null || !password.matches(".{2,}") || passConfirm == null
						|| !passConfirm.matches(".{2,}")) || !password.equals(passConfirm)) {
					errors.put("passwords", "Passwords either do not match or are too short.");
				}

				model.addAttribute("errors", errors);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "index";

	}
	
	@RequestMapping(value = "/massRegister", method = RequestMethod.POST)
	public String massRegistration(Model model){
		
		return "index";
	}

	/**
	 * Method to request for the edit page.
	 * 
	 * @param model
	 *            being passed.
	 * @return the edit page before post.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editPost(Model model) {

		// User in session.
		User u = getCurrentUser();

		u.setMajor(getMajorDao().findMajorByUser(u));
		u.setConcentration(getMajorDao().findConcentrationByUser(u));
		u.setMinor(getMajorDao().findMinorByUser(u));

		ArrayList<Major> m = getMajorDao().findAllMajors();

		System.out.println(u.toString());

		model.addAttribute("user", u);
		model.addAttribute("majors", m);

		HashMap<String, String> e = new HashMap<String, String>();
		model.addAttribute("errors", e);

		return "edit";

	}

	/**
	 * Method is after the user edits their information.
	 * 
	 * @param model
	 *            being passed.
	 * @param title
	 *            of user.
	 * @param firstName
	 *            of user.
	 * @param lastName
	 *            of user.
	 * @param suffix
	 *            of user.
	 * @param personalEmail
	 *            of user.
	 * @param graduationYear
	 *            of user.
	 * @param major
	 *            of user.
	 * @param doubleMajor
	 *            of user.
	 * @param thirdMajor
	 *            of user.
	 * @param occupation
	 *            of user.
	 * @param biography
	 *            of user.
	 * @param experience
	 *            of user.
	 * @param password
	 *            of user.
	 * @param confirmPassword
	 *            of user.
	 * @return The new information of the user.
	 * @throws IOException 
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(Model model, @RequestParam("title") String title, @RequestParam("fName") String firstName,
			@RequestParam("lName") String lastName, @RequestParam("suffix") String suffix,
			@RequestParam("personalEmail") String personalEmail, @RequestParam("graduationYear") String graduationYear,
			@RequestParam("major") String major, @RequestParam("doubleMajor") String doubleMajor,
			@RequestParam("thirdMajor") String thirdMajor, @RequestParam("occupation") String occupation,
			@RequestParam("bio") String biography, @RequestParam("experience") String experience,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, HttpServletRequest request, HttpServletResponse response,
			@RequestParam CommonsMultipartFile[] fileUpload, @RequestParam("file") MultipartFile[] files) throws IOException {

		if (validateEdit(password, confirmPassword, firstName, lastName, personalEmail, graduationYear)) {

			User u = getCurrentUser();

			u.setMajor(getMajorDao().findMajorByUser(u));
			u.setConcentration(getMajorDao().findConcentrationByUser(u));
			u.setMinor(getMajorDao().findMinorByUser(u));

			if (Validator.isNull(title))
				title = null;
			if (Validator.isNull(suffix))
				suffix = null;
			if (Validator.isNull(personalEmail))
				personalEmail = null;
			if (Validator.isNull(occupation))
				occupation = null;
			if (Validator.isNull(biography))
				biography = null;
			if (Validator.isNull(experience))
				experience = null;

			if (!Validator.validateSelect(graduationYear)) {
				u.setGraduationYear(0);
			} else {
				u.setGraduationYear(Integer.parseInt(graduationYear));
			}

			u.setTitle(title);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setSuffix(suffix);
			u.setPersonalEmail(personalEmail);
			u.setOccupation(occupation);
			u.setBio(biography);
			u.setExperience(experience);

			Major m = getMajorDao().findByName(major);
			Major m2 = getMajorDao().findByName(doubleMajor);
			Major m3 = getMajorDao().findByName(thirdMajor);

			u.clearMajors();
			if (m != null) {
				u.addMajor(m);
			}
			if (m2 != null) {
				u.addMajor(m2);
			}
			if (m2 != null) {
				u.addMajor(m3);
			}

			u.setPassword(pwEncoder.encode(password));

			try {
				getUserDao().updateUser(u);
				getMajorDao().updateMajorByUser(u);
			} catch (Exception e) {
				/* Probably should log this */
				System.out.println("Oops");

			}
			
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile("file");

			// UploadFile file = new UploadFile();
			// file.setFilename(multipartFile.getOriginalFilename());
			// file.setNotes(ServletRequestUtils.getStringParameter(request,
			// "notes"));
			// file.setType(multipartFile.getContentType());
			if (files[0] != null) {
				((UploadFile) files[0]).setData(multipartFile.getBytes());
				getFileUploadDao().addFile((UploadFile) files[0]);
			}
			if (files[1] != null) {
				((UploadFile) files[1]).setData(multipartFile.getBytes());
				getImageUploadDao().addImage((UploadFile) files[1]);
			}
			// if (fileUpload != null && fileUpload.length > 0) {
			// for (CommonsMultipartFile aFile : fileUpload){

			// System.out.println("Saving file: " + aFile.getOriginalFilename());

			// UploadFile uploadFile = new UploadFile();
			// uploadFile.setFileName(aFile.getOriginalFilename());
			// uploadFile.setData(aFile.getBytes());
			// fileUploadDao.save(uploadFile);
			// }
			// }

			return "userProfile";
		}

		HashMap<String, String> e = new HashMap<String, String>();// TODO

		ArrayList<Major> m = getMajorDao().findAllMajors();

		User u = getCurrentUser();

		u.setMajor(getMajorDao().findMajorByUser(u));
		u.setConcentration(getMajorDao().findConcentrationByUser(u));
		u.setMinor(getMajorDao().findMinorByUser(u));

		if (!Validator.validatePassword(password) || !Validator.validatePasswordsMatch(password, confirmPassword))
			e.put("password", "Invalid Password");
		if (!Validator.validateName(firstName))
			e.put("fName", "Invalid First Name Entry");
		if (!Validator.validateName(lastName))
			e.put("fName", "Invalid Last Name Entry");
		if (!Validator.validateEmail(personalEmail, false))
			e.put("fName", "Invalid Email Entry");

		model.addAttribute("user", u);
		model.addAttribute("majors", m);
		model.addAttribute("errors", e);

		return "edit";

	}

	@RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
	public String jobDisplay(Model model, @PathVariable Long id) {

		try {

			JobPosting currentJob = getJobPostingDao().getObjectById(id);
			model.addAttribute("currentJob", currentJob);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "jobDisplay";

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

		try {

			ArrayList<JobPosting> job = new ArrayList<JobPosting>();
			job = getJobPostingDao().findAll();

			model.addAttribute("jobPostings", job);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "jobList";
	}

	/**
	 * Accesses the user profile page. Dynamically grabs information depending
	 * who is logged in such as student, alumni, and faculty.
	 * 
	 * @param model
	 *            is being passed in.
	 * @param id
	 *            that belongs to that user.
	 * @return the profile page that belongs to the user.
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String userProfile(Model model, @PathVariable Long id) {

		User currentUser = getUserDao().getObjectById(id);
		model.addAttribute("profileUser", currentUser);

		return "userProfile";
	}

	/**
	 * Post method that displays after user profile is displayed.
	 * 
	 * @param model
	 *            being passed.
	 * @param request
	 * @param response
	 * @param fileUpload
	 *            to pass the file in.
	 * @param files
	 *            to pass the text file in.
	 * @return the profile after its displayed.
	 * @throws Exception
	 *             to file that is invalid.
	 */
//	@RequestMapping(value = "/userProfile", method = RequestMethod.POST)
//	public void userProfileUpload(Model model, HttpServletRequest request, HttpServletResponse response,
//			@RequestParam CommonsMultipartFile[] fileUpload, @RequestParam("file") MultipartFile[] files)
//					throws Exception {
//
//		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//		MultipartFile multipartFile = multipartRequest.getFile("file");
//
//		// UploadFile file = new UploadFile();
//		// file.setFilename(multipartFile.getOriginalFilename());
//		// file.setNotes(ServletRequestUtils.getStringParameter(request,
//		// "notes"));
//		// file.setType(multipartFile.getContentType());
//		if (files[0] != null) {
//			((UploadFile) files[0]).setData(multipartFile.getBytes());
//			getFileUploadDao().addFile((UploadFile) files[0]);
//		}
//		if (files[1] != null) {
//			((UploadFile) files[1]).setData(multipartFile.getBytes());
//			getImageUploadDao().addImage((UploadFile) files[1]);
//		}
		// if (fileUpload != null && fileUpload.length > 0) {
		// for (CommonsMultipartFile aFile : fileUpload){

		// System.out.println("Saving file: " + aFile.getOriginalFilename());

		// UploadFile uploadFile = new UploadFile();
		// uploadFile.setFileName(aFile.getOriginalFilename());
		// uploadFile.setData(aFile.getBytes());
		// fileUploadDao.save(uploadFile);
		// }
		// }

		//return "userProfile";
	//}

	/**
	 * Access to the Faculty Profile page.
	 * 
	 * @param model
	 *            is being passed in
	 * @return the faculty page.
	 */
	@RequestMapping(value = "/facultyProfile/{id}", method = RequestMethod.GET)
	public String faculty(Model model, @PathVariable Long id) {

		User currentUser = getUserDao().getObjectById(id);
		model.addAttribute("facultyUser", currentUser);

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

		// Return all Alumni
		try {
			ArrayList<User> faculty = new ArrayList<User>();
			faculty = getUserDao().findAllFaculty();

			model.addAttribute("faculty", faculty);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "facultyProfile";

	}

	/**
	 * Post method that faculty page.
	 * 
	 * @param model
	 *            being passed.
	 * @param request
	 * @param response
	 * @param fileUpload
	 * @return the faculty page after the user uploads their information.
	 * @throws Exception
	 *             is the file is invalid.
	 */
	@RequestMapping(value = "/facultyProfile", method = RequestMethod.POST)
	public String facultyUpload(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("file");

		UploadFile image = new UploadFile();
		// file.setFilename(multipartFile.getOriginalFilename());
		// file.setNotes(ServletRequestUtils.getStringParameter(request,
		// "notes"));
		// file.setType(multipartFile.getContentType());
		if (image != null) {
			image.setData(multipartFile.getBytes());
			getImageUploadDao().addImage(image);
		}

		// if (fileUpload != null && fileUpload.length > 0) {
		// for (CommonsMultipartFile aFile : fileUpload){
		//
		// System.out.println("Saving file: " + aFile.getOriginalFilename());

		// UploadFile file = new UploadFile();
		// getFileUploadDao().addFile(file);

		// uploadFile.setFileName(aFile.getOriginalFilename());
		// uploadFile.setData(aFile.getBytes());
		// fileUploadDao.save(uploadFile);
		// }
		// }

		if (getCurrentUser().getRole() <= 2) {
			return "userProfile";
		}

		return "facultyProfile";
	}

	/**
	 * Displays all the alumni users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @param page
	 *            that displays 15 users at a time.
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
	 * Displays all the alumni users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the alumni list page.
	 */
	@RequestMapping(value = "/alumniList", method = RequestMethod.GET)
	public String alumniList(Model model) {

		try {

			ArrayList<User> alumni = new ArrayList<User>();
			alumni = getUserDao().findAll();

			for (User users : alumni) {
				users.setMajor(getMajorDao().findMajorByUser(users));
				users.setConcentration(getMajorDao().findConcentrationByUser(users));
				users.setMinor(getMajorDao().findMinorByUser(users));
			}

			sortUsers(alumni);

			model.addAttribute("alumni", alumni);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "alumniList";
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

	private boolean validateEdit(String password, String confirmPassword, String firstName, String lastName,
			String personalEmail, String graduationYear) {

		return (Validator.validatePasswordsMatch(password, confirmPassword) && Validator.validatePassword(password)
				&& Validator.validateName(firstName) && Validator.validateName(lastName)
				&& Validator.validateGraduationYear(graduationYear, false)
				&& Validator.validateEmail(personalEmail, false));
	}
}