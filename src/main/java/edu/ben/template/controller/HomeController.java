package edu.ben.template.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import edu.ben.template.model.Event;
import edu.ben.template.model.Job;
import edu.ben.template.model.Major;
import edu.ben.template.model.Title;
import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;
import edu.ben.template.model.Validator;

@Controller
@SessionAttributes({ "editJob", "currentJob", "editEvent", "currentEvent", "profileUser"})
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
	 * @return to the homepage template of Alumni Tracker.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		ArrayList<Event> events;

		try {
			events = getEventDao().getAll();
		} catch (Exception e) {
			e.printStackTrace();
			events = new ArrayList<Event>();
		}

		ArrayList<Job> jobs;
		try {
			jobs = getJobDao().getByHighestPaidSalary();
		} catch (Exception e) {
			e.printStackTrace();
			jobs = new ArrayList<Job>();
		}

		ArrayList<Event> eventDisplay = new ArrayList<Event>();
		int countEvent = 0;
		for (int i = events.size() - 1; i >= 0 && countEvent < 4; i--) {
			eventDisplay.add(events.get(i));
			countEvent++;
		}

		ArrayList<Job> jobDisplay = new ArrayList<Job>();
		int countJob = 0;
		for (int i = jobs.size() - 1; i >= 0 && countJob < 6; i--) {
			jobDisplay.add(jobs.get(i));
			countJob++;
		}

		model.addAttribute("events", eventDisplay);
		model.addAttribute("jobs", jobDisplay);
		model.addAttribute("active", "index");

		return "indexTemplate";
	}

	@RequestMapping(value = "/createJobPosting", method = RequestMethod.GET)
	public String createJob(Model model) {
		model.addAttribute("active", "job");
		return "createJobTemplate";
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
			@RequestParam("company") String company, @RequestParam("location") String location,
			@RequestParam("hours") int hours, @RequestParam("startSalary") String startSalary,
			@RequestParam("endSalary") String endSalary, @RequestParam("description") String description,
			@RequestParam(value = "public", required = false) boolean isPublic) {

		// HOURS 1 for part time, 2 for full time
		// -1 for empty, -2 for error
		int startingSalary = startSalary == null ? -1
				: (startSalary.matches("[0-9]{1,}") ? Integer.parseInt(startSalary) : -2);
		int endingSalary = endSalary == null ? -1 : (endSalary.matches("[0-9]{1,}") ? Integer.parseInt(endSalary) : -2);

		if (name != null && name.matches(".{2,}") && company != null && company.matches(".{2,}") && description != null
				&& description.matches(".{2,}") && location != null && location.matches(".{2,}")
				&& (hours == 1 || hours == 2) && startingSalary != -2 && endingSalary != -2) {

			User currentUser = getCurrentUser();

			Job job = new Job(name, description, company, currentUser);

			if (isPublic) {
				job.setToPublic(1);
			} else {
				job.setToPublic(0);
			}

			job.setHours(hours);

			if (startingSalary != -1 && endingSalary != -1 && startingSalary < endingSalary) {
				job.setStart_salary(startingSalary);
				job.setEnd_salary(endingSalary);
			} else if (!(startingSalary < endingSalary)) {

				HashMap<String, String> errors = new HashMap<String, String>();

				if (name == null || !name.matches(".{2,}")) {
					errors.put("name", "Input error on the job's name.");
				}

				if (company == null || !company.matches(".{2,}")) {
					errors.put("company", "Input error on the job's company.");
				}

				if (description == null || !description.matches(".{2,}")) {
					errors.put("description", "Input error on the job description.");
				}

				if (location == null || !location.matches(".{2,}")) {
					errors.put("location", "Input error on the job's location.");
				}

				if (hours != 1 && hours != 2) {
					errors.put("hours", "Input error on the job's work hours type.");
				}

				// -2 means error in the input
				if (startingSalary == -2 || endingSalary == -2) {
					errors.put("salary", "Input error on salaries.");
				} else if (endingSalary < startingSalary) {
					errors.put("salary", "Start salary must be less than the end salary.");
				}

				model.addAttribute("errors", errors);
				model.addAttribute("active", "job");

				return "createJobTemplate";
			}

			job.setLocation(location);

			try {
				getJobDao().addJob(job);
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.addAttribute("jobCreation", true);

			model.addAttribute("active", "job");

			try {
				ArrayList<Job> jobs = new ArrayList<Job>();
				jobs = getJobDao().getAll();

				model.addAttribute("jobs", jobs);

			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("active", "job");
			return "jobsTemplate";

		} else {

			HashMap<String, String> errors = new HashMap<String, String>();

			if (name == null || !name.matches(".{2,}")) {
				errors.put("name", "Input error on the job's name.");
			}

			if (company == null || !company.matches(".{2,}")) {
				errors.put("company", "Input error on the job's company.");
			}

			if (description == null || !description.matches(".{2,}")) {
				errors.put("description", "Input error on the job description.");
			}

			if (location == null || !location.matches(".{2,}")) {
				errors.put("location", "Input error on the job's location.");
			}

			if (hours != 1 && hours != 2) {
				errors.put("hours", "Input error on the job's work hours type.");
			}

			// -2 means error in the input
			if (startingSalary == -2 || endingSalary == -2) {
				errors.put("salary", "Input error on salaries.");
			} else if (endingSalary < startingSalary) {
				errors.put("salary", "Start salary must be less than the end salary.");
			}

			model.addAttribute("errors", errors);
			model.addAttribute("active", "job");

			return "createJobTemplate";
		}
	}
	

	@RequestMapping(value = "/editAJob/{id}", method = RequestMethod.GET)
	public String editAJob(Model model, @PathVariable Long id) {
		Job editJob = getJobDao().getObjectById(id);
		// Long jobId = editJob.getId();
		model.addAttribute("editJob", editJob);
		// model.addAttribute("editJobId", jobId);
		return "editJobTemplate";
	}

	@RequestMapping(value = "/editAJob", method = RequestMethod.POST)
	public String editAJobPost(Model model, @RequestParam("name") String name, @RequestParam("company") String company,
			@RequestParam("description") String description, @RequestParam("location") String location,
			@RequestParam("startSalary") int startSalary, @RequestParam("endSalary") int endSalary,
			@RequestParam("startWage") float startWage, @RequestParam("endWage") float endWage,
			@RequestParam("hours") int hours, @RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @ModelAttribute("editJob") Job editJob) {

		if (name != null && name.matches(".{2,}") && company != null && company.matches(".{2,}") && description != null
				&& description.matches(".{2,}") && location != null && location.matches(".{2,}")) {

			if (startSalary > 0 && endSalary > 0) {
				editJob.setSalary(true);
				editJob.setStart_salary(startSalary);
				editJob.setEnd_salary(endSalary);
				editJob.setStart_wage(0);
				editJob.setEnd_wage(0);
			} else {
				editJob.setSalary(false);
				editJob.setStart_wage(startWage);
				editJob.setEnd_wage(endWage);
				editJob.setStart_salary(0);
				editJob.setEnd_salary(0);
			}
			try {
				getJobDao().updateJob(editJob);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/jobs";
		} else {
			HashMap<String, String> errors = new HashMap<String, String>();

			if (name == null || !name.matches(".{2,}")) {
				errors.put("name", "Error in the input for the job name.");
			}

			if (company == null || !company.matches(".{2,}")) {
				errors.put("company", "Error in the input for the job's company.");
			}

			if (location == null || !location.matches(".{2,}")) {
				errors.put("location", "Error in the input for the job location.");
			}
			if (hours != 1 || hours != 2) {
				errors.put("hours", "Error in the input for the job hours.");
			}

			if (description == null || !description.matches(".{2,}")) {
				errors.put("description", "Error in the input for the job description.");
			}

			model.addAttribute("errors", errors);

			return "editJobTemplate";
		}

	}

	/**
	 * Method to request the Get for creating an event.
	 * 
	 * @param model
	 *            being passed.
	 * @return createEvent page
	 */
	@RequestMapping(value = "/createNewEvent", method = RequestMethod.GET)
	public String createEventTemplate(Model model) {
		model.addAttribute("active", "event");
		return "createEventTemplate";
	}

	/**
	 * Method to request the Post for creating an event.
	 * 
	 * @param model
	 *            being passed.
	 * @return createEvent page
	 */
	@RequestMapping(value = "/createNewEvent", method = RequestMethod.POST)
	public String createNewEventPost(Model model, @RequestParam("name") String name,
			@RequestParam("date") String dateStr, @RequestParam("description") String description,
			@RequestParam("location") String location, @RequestParam("startTime") String startTime,

			@RequestParam("endTime") String endTime,
			@RequestParam(value = "public", required = false) boolean isPublic) {

		// TODO FINISH THIS METHOD
		Date currentDate = new Date(System.currentTimeMillis());

		if (name != null && name.matches(".{2,}") && description != null && description.matches(".{2,}")
				&& location != null && location.matches(".{2,}") && dateStr != null
				&& dateStr.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}") && startTime.matches("[0-9]{2}:[0-9]{2}")
				&& endTime.matches("[0-9]{2}:[0-9]{2}")) {

			int hour = 0;
			int min = 1;

			String[] startTDiv = startTime.split(":");
			String[] endTDiv = endTime.split(":");

			int startHour = Integer.parseInt(startTDiv[hour]);
			int startMin = Integer.parseInt(startTDiv[min]);

			int endHour = Integer.parseInt(endTDiv[hour]);
			int endMin = Integer.parseInt(endTDiv[min]);

			String[] datePart = dateStr.split("/");

			// Subtracted 1900 from year and 1 from month to offset the //
			// deprecated constructor
			Date eventDate = new Date(Integer.parseInt(datePart[2]) - 1900, Integer.parseInt(datePart[0]) - 1,
					Integer.parseInt(datePart[1]));

			User u = getCurrentUser();

			Event createEvent = new Event();

			createEvent.setName(name);
			createEvent.setDescription(description);
			createEvent.setLocation(location);
			createEvent.setPoster(u);

			// ERROR CHECK AFTER DATE FAILED
			if (eventDate.compareTo(currentDate) <= 0) {

				HashMap<String, String> errors = new HashMap<String, String>();

				errors.put("date", "Error. The event's date must be after the current date.");

				if (name == null || !name.matches(".{2,}")) {
					errors.put("name", "Error in the input for the event name.");
				}

				if (location == null || !location.matches(".{2,}")) {
					errors.put("location", "Error in the input for the events' location.");
				}

				if (description == null || !description.matches(".{2,}")) {
					errors.put("description", "Error in the input for the event description.");
				}

				if ((startHour > endHour) || (startHour == endHour && startMin >= endMin)) {
					errors.put("times", "Error. The event's starting time must happen before the ending time.");
				}

				model.addAttribute("errors", errors);

				return "/createEventTemplate";
			}

			// TODO ERROR CHECK AFTER TIMES FAILED
			if ((startHour > endHour) || (startHour == endHour && startMin >= endMin)) {

				HashMap<String, String> errors = new HashMap<String, String>();

				errors.put("times", "Error. The event's starting time must happen before the ending time.");

				if (name == null || !name.matches(".{2,}")) {
					errors.put("name", "Error in the input for the event name.");
				}

				if (location == null || !location.matches(".{2,}")) {
					errors.put("location", "Error in the input for the event's location.");
				}

				if (description == null || !description.matches(".{2,}")) {
					errors.put("description", "Error in the input for the event description.");
				}

				if (dateStr == null || !dateStr.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
					errors.put("date", "Error in the input for the event's date.");
				} else if (eventDate.compareTo(currentDate) <= 0) {
					errors.put("date", "Error. The event's date must be after the current date.");
				}

				model.addAttribute("errors", errors);

				return "/createEventTemplate";

			}

			createEvent.setDate(eventDate);
			createEvent.setStartTime(startTime);
			createEvent.setEndTime(endTime);

			if (isPublic) {
				createEvent.setToPublic(1);
			}

			model.addAttribute("eventCreation", true);

			try {
				getEventDao().addEvent(createEvent);
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				ArrayList<Event> events = new ArrayList<Event>();
				events = getEventDao().getAll();

				model.addAttribute("events", events);

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "eventsTemplate";

		} else {

			HashMap<String, String> errors = new HashMap<String, String>();

			if (name == null || !name.matches(".{2,}")) {
				errors.put("name", "Error in the input for the event name.");
			}

			if (location == null || !location.matches(".{2,}")) {
				errors.put("location", "Error in the input for the events' location.");
			}

			if (description == null || !description.matches(".{2,}")) {
				errors.put("description", "Error in the input for the event description.");
			}

			boolean nullDate = false;
			if (dateStr == null || !dateStr.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
				errors.put("date", "Error in the input for the event's date.");
				nullDate = true;
			}
			if (!nullDate) {
				String[] datePart = dateStr.split("/");
				Date eventDate = null;
				try {
					eventDate = new Date(Integer.parseInt(datePart[2]) - 1900, Integer.parseInt(datePart[0]) - 1,
							Integer.parseInt(datePart[1]));
				} catch (Exception e) {
					e.printStackTrace();

					errors.put("date", "Invalid input for the event's date.");
				}

				if (eventDate == null || eventDate.compareTo(currentDate) <= 0) {
					errors.put("date", "Error. The event's date must be after the current date.");
				}
			}

			if (startTime == null || endTime == null || !startTime.matches("[0-9]{2}:[0-9]{2}")
					|| !endTime.matches("[0-9]{2}:[0-9]{2}")) {
				errors.put("times", "Invalid input for start time and/or end time.");
			} else {
				int hour = 0;
				int min = 1;

				String[] startTDiv = startTime.split(":");
				String[] endTDiv = endTime.split(":");

				int startHour = Integer.parseInt(startTDiv[hour]);
				int startMin = Integer.parseInt(startTDiv[min]);

				int endHour = Integer.parseInt(endTDiv[hour]);
				int endMin = Integer.parseInt(endTDiv[min]);

				if ((startHour > endHour) || (startHour == endHour && startMin >= endMin)) {
					errors.put("times", "Error. The event's starting time must happen before the ending time.");
				}
			}

			model.addAttribute("errors", errors);

			return "createEventTemplate";
		}
	}

	@RequestMapping(value = "/editAnEvent/{id}", method = RequestMethod.GET)
	public String editAnEvent(Model model, @PathVariable Long id) {
		Event editEvent = getEventDao().getObjectById(id);

		model.addAttribute("editEvent", editEvent);

		return "editEventTemplate";
	}

	@RequestMapping(value = "/editAnEvent", method = RequestMethod.POST)
	public String editJobAPost(Model model, @RequestParam("name") String name, @RequestParam("company") String company,
			@RequestParam("description") String description, @RequestParam("location") String location,
			@RequestParam("date") String date, @ModelAttribute("editEvent") Event editEvent) {

		if (name != null && name.matches(".{2,}") && company != null && company.matches(".{2,}") && description != null
				&& description.matches(".{2,}") && location != null && location.matches(".{2,}")) {

			String[] datePart = date.split("/");

			// Subtracted 1900 from year and 1 from month to offset the
			// deprecated constructor
			Date eventDate = new Date(Integer.parseInt(datePart[2]) - 1900, Integer.parseInt(datePart[0]) - 1,
					Integer.parseInt(datePart[1]));
			Date currentDate = new Date(System.currentTimeMillis());

			editEvent.setDate(eventDate);

			try {
				getEventDao().updateEvent(editEvent);

			} catch (Exception e) {

			}

			return "redirect:/events";
		} else {
			HashMap<String, String> errors = new HashMap<String, String>();

			if (name == null || !name.matches(".{2,}")) {
				errors.put("name", "Error in the input for the job name.");
			}

			if (company == null || !company.matches(".{2,}")) {
				errors.put("company", "Error in the input for the job's company.");
			}

			if (location == null || !location.matches(".{2,}")) {
				errors.put("location", "Error in the input for the event name.");
			}

			if (description == null || !description.matches(".{2,}")) {
				errors.put("description", "Error in the input for the job description.");
			}

			model.addAttribute("errors", errors);

			return "editEventTemplate";
		}
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
			events = getEventDao().getAll();

			model.addAttribute("events", events);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("active", "event");
		return "events";
	}

	/**
	 * Displays all events.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the page returning all the events being posted.
	 */
	@RequestMapping(value = "/eventsTemplate", method = RequestMethod.GET)
	public String events(Model model) {

		try {
			ArrayList<Event> events = new ArrayList<Event>();
			events = getEventDao().getAll();

			model.addAttribute("events", events);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "eventsTemplate";
	}

	@RequestMapping(value = "/newEvents/{id}", method = RequestMethod.GET)
	public String eventSingle(Model model, @PathVariable Long id) {

		try {

			Event currentEvent = getEventDao().getObjectById(id);
			model.addAttribute("currentEvent", currentEvent);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "eventSingle";

	}
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public String deleteEvent(Model model, @ModelAttribute("currentEvent") Event currentEvent) {
		getEventDao().deleteEvent(currentEvent.getId());
		return "eventsTemplate";
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

		return "registration";
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

				// TITLE is now TitleId
				// if (title == null || title.equals("")) {
				// register.setTitleID(null);
				// } else {
				// register.setTitleID(title);
				// }

				if (suffix == null || suffix.equals("")) {
					register.setSuffix(null);
				} else {
					register.setSuffix(suffix);
				}

				register.setPassword(pwEncoder.encode(password));

				getUserDao().addUser(register);

				model.addAttribute("active", "index");
				return "indexTemplate";

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
				return "registration";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "registration";
	}

	@RequestMapping(value = "/massRegister", method = RequestMethod.POST)
	public String massRegistration(Model model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam CommonsMultipartFile[] multiple) throws IOException, SerialException, SQLException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("multiple");

		UploadFile file = new UploadFile();
		file.setFileName(multipartFile.getOriginalFilename());
		// file.setNotes(ServletRequestUtils.getStringParameter(request,
		// "notes"));
		// file.setType(multipartFile.getContentType());
		if (file != null) {
			byte[] bytes = multipartFile.getBytes();
			Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			file.setData((com.mysql.jdbc.Blob) blob);
			getUserDao().addMultiple(file.getFileName());
		}
		// model.addAttribute("active", "index");
		return "admin";
	}

	@RequestMapping(value = "/getImage/{id}", method = RequestMethod.GET)
	public void image(Model model, @PathVariable Long id) throws SQLException, IOException {
		User profileUser = getUserDao().getObjectById(id);
		// gets the image object based on the image id
		if (profileUser.getImageId() != null) {
			UploadFile userPhoto = getImageUploadDao().getObjectById(profileUser.getImageId());
			// User userProfile = userPhoto.getProfile();
			byte buff[] = new byte[1024];
			Blob profilePic = userPhoto.getData();
			// response.setContentType("image/jpeg, image/jpg, image/png,
			// image/gif");
			File newPic = new File("image.jpeg");
			InputStream is = profilePic.getBinaryStream();
			FileOutputStream fos = new FileOutputStream(newPic);
			for (int i = is.read(buff); i != -1; i = is.read(buff)) {
				fos.write(buff, 0, i);
			}
			is.close();
			fos.close();
			// model.addAttribute("photo", userPhoto);
		}
	}

	/**
	 * Method to request for the edit page.
	 * 
	 * @param model
	 *            being passed.
	 * @return the edit page before post.
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPost(Model model, @PathVariable Long id) throws SQLException, IOException {

		// User in session.
		User u = getCurrentUser();
		User profileUser = getUserDao().getObjectById(id);
		// gets the image object based on the image id
		if (profileUser.getImageId() != null) {
			UploadFile userPhoto = getImageUploadDao().getObjectById(profileUser.getImageId());
			// User userProfile = userPhoto.getProfile();
			byte buff[] = new byte[1024];
			Blob profilePic = userPhoto.getData();
			// response.setContentType("image/jpeg, image/jpg, image/png,
			// image/gif");
			File newPic = new File("image.jpeg");
			InputStream is = profilePic.getBinaryStream();
			FileOutputStream fos = new FileOutputStream(newPic);
			for (int i = is.read(buff); i != -1; i = is.read(buff)) {
				fos.write(buff, 0, i);
			}
			is.close();
			fos.close();
			model.addAttribute("photo", userPhoto);
		}
		profileUser.setMajor(getMajorDao().getMajorByUser(profileUser));
		profileUser.setConcentration(getMajorDao().getConcentrationByUser(profileUser));
		profileUser.setMinor(getMajorDao().getMinorByUser(profileUser));

		// Change this to a u.setTitle(); --> Refactor TitleId To a String

		Title title = getTitleDao().getObjectById(u.getTitleID());

		ArrayList<Major> m = getMajorDao().getAllMajors();
		ArrayList<Title> t = getTitleDao().getAll();

		model.addAttribute("user", profileUser);
		model.addAttribute("majors", m);
		model.addAttribute("titles", t);
		model.addAttribute("title", title);

		// For User Error Checking
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
	 * @throws SQLException
	 * @throws SerialException
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String edit(Model model, @RequestParam("title") String title, @RequestParam("fName") String firstName,
			@RequestParam("lName") String lastName, @RequestParam("suffix") String suffix,
			@RequestParam("personalEmail") String personalEmail, @RequestParam("graduationYear") String graduationYear,
			@RequestParam("major") String major, @RequestParam("doubleMajor") String doubleMajor,
			@RequestParam("thirdMajor") String thirdMajor, @RequestParam("occupation") String occupation,
			@RequestParam("biography") String biography, @RequestParam("experience") String experience,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword/*
																												 * ,
																												 * HttpServletRequest
																												 * request,
																												 * HttpServletResponse
																												 * response, @RequestParam
																												 * CommonsMultipartFile
																												 * []
																												 * fileUpload,
																												 * 
																												 * @RequestParam
																												 * ("file")
																												 * MultipartFile
																												 * []
																												 * files, @RequestParam
																												 * ("photo")
																												 * File
																												 * photo,
																												 * 
																												 * @RequestParam
																												 * (
																												 * "resume")
																												 * File
																												 * resume
																												 */)
					throws IOException {

		if (validateEdit(firstName, lastName, personalEmail)) {

			User u = getCurrentUser();

			if (!Validator.validateSelect(graduationYear)) {
				u.setGraduationYear((Integer.parseInt(graduationYear)));
			}
			if (!major.equals("Select") && !getMajorDao().getByName(major).equals(null)
					&& getMajorDao().getByName(major) != null) {
				u.addMajor(getMajorDao().getByName(major));
			}
			if (!doubleMajor.equals("Select") && !getMajorDao().getByName(doubleMajor).equals(null)
					&& getMajorDao().getByName(doubleMajor) != null) {
				u.addMajor(getMajorDao().getByName(doubleMajor));
			}
			if (!thirdMajor.equals("Select") && !getMajorDao().getByName(thirdMajor).equals(null)
					&& getMajorDao().getByName(thirdMajor) != null) {
				u.addMajor(getMajorDao().getByName(thirdMajor));
			}

			if (!title.equals("Select") && !getTitleDao().getObjectByName(title).equals(null)) {
				u.setTitleID(getTitleDao().getObjectByName(title).getId());
			}

			if (!Validator.validateSelect(graduationYear)) {
				u.setGraduationYear(0);
			} else {
				u.setGraduationYear(Integer.parseInt(graduationYear));
			}

			// u.setTitle(title);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setSuffix(suffix);
			u.setPersonalEmail(personalEmail);
			u.setOccupation(occupation);
			u.setBio(biography);
			u.setExperience(experience);

			try {
				getUserDao().updateUser(u);
				getMajorDao().updateMajorAndConcentrationByUser(u);
			} catch (Exception e) {

			}

			// MultipartHttpServletRequest multipartRequest =
			// (MultipartHttpServletRequest) request;
			// MultipartFile multipartFile = multipartRequest.getFile("file");

			// file.setFilename(multipartFile.getOriginalFilename());
			// file.setNotes(ServletRequestUtils.getStringParameter(request,
			// "notes"));
			// file.setType(multipartFile.getContentType());

			// if (resume != null) {
			// // if (files[0] != null) {
			// UploadFile resumeFile = new UploadFile();
			// (resumeFile).setData(multipartFile.getBytes());
			// getFileUploadDao().addFile(resumeFile);
			// }
			//
			// if (photo != null) {
			// // if (files[1] != null) {
			// UploadFile photoFile = new UploadFile();
			// (photoFile).setData(multipartFile.getBytes());
			// getImageUploadDao().addImage(photoFile);
			// }

			// if (resumeUpload != null) {
			// MultipartHttpServletRequest multipartRequest =
			// (MultipartHttpServletRequest) request;
			// MultipartFile multipartFile = multipartRequest.getFile("resume");
			// // if (files[0] != null) {
			// UploadFile resumeFile = new UploadFile();
			// byte[] bytes = multipartFile.getBytes();
			// Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			// resumeFile.setData((com.mysql.jdbc.Blob) blob);
			// getFileUploadDao().addFile(resumeFile);
			// }
			//
			// if (profileUpload != null) {
			// MultipartHttpServletRequest multipartRequest =
			// (MultipartHttpServletRequest) request;
			// MultipartFile multipartFile =
			// multipartRequest.getFile("profile");
			// // if (files[1] != null) {
			// UploadFile photoFile = new UploadFile();
			// byte[] bytes = multipartFile.getBytes();
			// Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			// photoFile.setData((com.mysql.jdbc.Blob) blob);
			// getImageUploadDao().addImage(photoFile);
			// // gets the right photo from the user that uploaded it
			// UploadFile photoFileObj =
			// getImageUploadDao().getObjectByUserId(profileUser.getId());
			// // sets the image id to the user
			// profileUser.setImageId(photoFileObj.getId());
			// }

			// if (fileUpload != null && fileUpload.length > 0) {
			// for (CommonsMultipartFile aFile : fileUpload){

			// System.out.println("Saving file: " +
			// aFile.getOriginalFilename());

			// UploadFile uploadFile = new UploadFile();
			// uploadFile.setFileName(aFile.getOriginalFilename());
			// uploadFile.setData(aFile.getBytes());
			// getFileUploadDao().addFile(uploadFile);
			// }
			// }

			if (validatePassword(password, confirmPassword)) {
				u.setPassword(pwEncoder.encode(password));
			}

			try {
				getUserDao().updateUser(u);
				getMajorDao().updateMajorAndConcentrationByUser(u);
			} catch (Exception e) {

			}

			return "redirect:/user/" + u.getId();
		}
		return "edit";
	}
	//
	// HashMap<String, String> e = new HashMap<String, String>();// TODO
	//
	// ArrayList<Major> m = getMajorDao().getAllMajors();
	//
	// User u = getCurrentUser();
	//
	// u.setMajor(getMajorDao().getMajorByUser(u));
	// u.setConcentration(getMajorDao().getConcentrationByUser(u));
	// u.setMinor(getMajorDao().getMinorByUser(u));
	//
	// if (!Validator.validatePassword(password) ||
	// !Validator.validatePasswordsMatch(password, confirmPassword))
	// e.put("password", "Invalid Password");
	// if (!Validator.validateName(firstName))
	// e.put("fName", "Invalid First Name Entry");
	// if (!Validator.validateName(lastName))
	// e.put("fName", "Invalid Last Name Entry");
	// if (!Validator.validateEmail(personalEmail, false))
	// e.put("fName", "Invalid Email Entry");
	//
	// model.addAttribute("user", u);
	// model.addAttribute("majors", m);
	// model.addAttribute("errors", e);
	//
	// return "edit";
	//
	// }

	@RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
	public String jobsSingle(Model model, @PathVariable Long id) {

		try {

			Job currentJob = getJobDao().getObjectById(id);
			model.addAttribute("currentJob", currentJob);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "jobsSingleTemplate";

	}
	
	@RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
	public String deleteJob(Model model, @ModelAttribute("currentJob") Job currentJob) {
		getJobDao().deleteJob(currentJob.getId());
		return "jobsTemplate";
	}

	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public String jobs(Model model) {
		try {
			ArrayList<Job> job = new ArrayList<Job>();
			job = getJobDao().getAll();

			model.addAttribute("jobs", job);

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("active", "job");
		return "jobsTemplate";
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
	 * @throws IOException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String userProfile(Model model, @PathVariable Long id) throws IOException, SQLException {

		User profileUser = getUserDao().getObjectById(id);

		// User profileUser = getUserDao().getObjectById(id);
		// gets the image object based on the image id
		if (profileUser.getImageId() != null) {
			UploadFile userPhoto = getImageUploadDao().getObjectById(profileUser.getImageId());
			// User userProfile = userPhoto.getProfile();
			byte buff[] = new byte[1024];
			Blob profilePic = userPhoto.getData();
			// response.setContentType("image/jpeg, image/jpg, image/png,
			// image/gif");
			File newPic = new File("image.jpeg");
			InputStream is = profilePic.getBinaryStream();
			FileOutputStream fos = new FileOutputStream(newPic);
			for (int i = is.read(buff); i != -1; i = is.read(buff)) {
				fos.write(buff, 0, i);
			}
			is.close();
			fos.close();
			model.addAttribute("photo", userPhoto);
		}

		profileUser.setMajor(getMajorDao().getMajorByUser(profileUser));
		profileUser.setConcentration(getMajorDao().getConcentrationByUser(profileUser));
		profileUser.setMinor(getMajorDao().getMinorByUser(profileUser));
		Title currentUserTitle = getTitleDao().getObjectById(profileUser.getTitleID());
		model.addAttribute("profileUser", profileUser);
		model.addAttribute("title", currentUserTitle);

		return "profile";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public String userProfileUpload(Model model, @RequestParam CommonsMultipartFile[] fileUpload) throws Exception {

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

	/**
	 * Displays all the alumni users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the alumni list page.
	 */
	@RequestMapping(value = "/alumni", method = RequestMethod.GET)
	public String alumniList(@RequestParam(required = false) Integer page, Model model) {

		try {

			ArrayList<User> alumni = new ArrayList<User>();
			alumni = getUserDao().getAll();

			for (User users : alumni) {
				users.setMajor(getMajorDao().getMajorByUser(users));
				users.setConcentration(getMajorDao().getConcentrationByUser(users));
				users.setMinor(getMajorDao().getMinorByUser(users));
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

		model.addAttribute("active", "alumni");
		return "alumni";
	}

	/**
	 * Displays all the faculty users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the faculty list page.
	 */
	@RequestMapping(value = "/faculty", method = RequestMethod.GET)
	public String facultyList(@RequestParam(required = false) Integer page, Model model) {

		try {

			ArrayList<User> faculty = new ArrayList<User>();
			faculty = getUserDao().getAll();

			for (User users : faculty) {
				users.setMajor(getMajorDao().getMajorByUser(users));
				users.setConcentration(getMajorDao().getConcentrationByUser(users));
				users.setMinor(getMajorDao().getMinorByUser(users));
			}

			sortUsers(faculty);

			if (page == null) {
				page = 0;
			}

			ArrayList<User> facTemp = new ArrayList<User>();

			for (int i = 0; i < faculty.size(); i++) {
				if (faculty.get(i).getRole() == 3) {
					facTemp.add(faculty.get(i));
				}
			}

			ArrayList<User> facultyList = new ArrayList<User>();

			for (int i = page * 15; i < page * 15 + 15; i++) {
				if (i < facTemp.size()) {
					facultyList.add(facTemp.get(i));
				}
			}
			model.addAttribute("facultyCount", facTemp.size());
			model.addAttribute("faculty", facultyList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("active", "faculty");
		return "faculty";
	}

	/**
	 * Displays all the users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the alumni list page.
	 */
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public String userList(@RequestParam(required = false) Integer page, Model model) {

		try {

			ArrayList<User> allUser = new ArrayList<User>();
			allUser = getUserDao().getAll();
			//
			// User profileUser = getUserDao().getObjectById(id);
			// // gets the image object based on the image id
			// if (profileUser.getImageId() != null) {
			// UploadFile userPhoto =
			// getImageUploadDao().getObjectById(profileUser.getImageId());
			// // User userProfile = userPhoto.getProfile();
			// byte buff[] = new byte[1024];
			// Blob profilePic = userPhoto.getData();
			// // response.setContentType("image/jpeg, image/jpg, image/png,
			// // image/gif");
			// File newPic = new File("image.jpeg");
			// InputStream is = profilePic.getBinaryStream();
			// FileOutputStream fos = new FileOutputStream(newPic);
			// for (int i = is.read(buff); i != -1; i = is.read(buff)) {
			// fos.write(buff, 0, i);
			// }
			// is.close();
			// fos.close();
			// //model.addAttribute("photo", userPhoto);
			// }

			for (User users : allUser) {
				users.setMajor(getMajorDao().getMajorByUser(users));
				users.setConcentration(getMajorDao().getConcentrationByUser(users));
				users.setMinor(getMajorDao().getMinorByUser(users));
			}

			sortUsers(allUser);
			if (page == null) {
				page = 0;
			}
			ArrayList<User> users = new ArrayList<User>();
			for (int i = page * 15; i < page * 15 + 15; i++) {

				if (i < allUser.size()) {

					users.add(allUser.get(i));
				}
			}
			model.addAttribute("users", users);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("active", "users");
		return "admin";
	}

	/**
	 * Displays all the users in the system.
	 * 
	 * @param model
	 *            being passed in.
	 * @return the alumni list page.
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(Model model, @ModelAttribute("profileUser") User profileUser) {
		profileUser.setActive(false);
		profileUser.setHidden(true);

		getUserDao().updateUser(profileUser);
		return "admin";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/somethingSecret", method = RequestMethod.GET)
	public String viewSecret(Model model) {
		return "secret";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userHome(Model model) {
		return "indexTemplate";
	}

	private boolean validateEdit(String password, String confirmPassword, String firstName, String lastName,
			String personalEmail, String graduationYear) {

		return (Validator.validatePasswordsMatch(password, confirmPassword) && Validator.validatePassword(password)
				&& Validator.validateName(firstName) && Validator.validateName(lastName)
				&& Validator.validateGraduationYear(graduationYear, false)
				&& Validator.validateEmail(personalEmail, false));
	}

	private boolean validatePassword(String password, String confirmPassword) {

		return (Validator.validatePasswordsMatch(password, confirmPassword) && Validator.validatePassword(password));
	}

	private boolean validateEdit(/* String password, String confirmPassword, */ String firstName, String lastName,
			String personalEmail) {

		return (/*
				 * Validator.validatePasswordsMatch(password, confirmPassword)
				 * && Validator.validatePassword(password) &&
				 */Validator.validateName(firstName) && Validator.validateName(lastName)
				&& Validator.validateEmail(personalEmail, false));
	}

}