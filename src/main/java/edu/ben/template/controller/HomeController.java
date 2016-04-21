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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.ben.template.model.Event;
import edu.ben.template.model.Interest;
import edu.ben.template.model.Job;
import edu.ben.template.model.Major;
import edu.ben.template.model.Title;
import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;
import edu.ben.template.model.Validator;

@Controller
@SessionAttributes({ "editJob", "currentJob", "editEvent", "currentEvent", "profileUser" })
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
	 * Sort the Events by the date.
	 * 
	 * @param events
	 */
	public void sortEvents(ArrayList<Event> events) {

		Collections.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				return o1.getDate().compareTo(o2.getDate());
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
	public String index(Model model) throws Exception{

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

			model.addAttribute("jobCreation", "true");

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

	@RequestMapping(value = "editAJob/{id}", method = RequestMethod.POST)
	public String editAJobPost(Model model, @PathVariable Long id, @RequestParam("name") String name,
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
			// (name, description, company, currentUser
			Job editJob = getJobDao().getObjectById(id);
			editJob.setName(name);
			editJob.setDescription(description);
			editJob.setCompany(company);

			if (isPublic) {
				editJob.setToPublic(1);
			} else {
				editJob.setToPublic(0);
			}

			editJob.setHours(hours);

			if (startingSalary != -1 && endingSalary != -1 && startingSalary < endingSalary) {
				editJob.setStart_salary(startingSalary);
				editJob.setEnd_salary(endingSalary);
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

				return "editJobTemplate";
			}

			editJob.setLocation(location);

			try {
				getJobDao().updateJob(editJob);
			} catch (Exception e) {
				e.printStackTrace();
			}

			model.addAttribute("jobCreation", "true");

			model.addAttribute("active", "job");

			try {
				ArrayList<Job> jobs = new ArrayList<Job>();
				jobs = getJobDao().getAll();

				model.addAttribute("jobs", jobs);

			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("active", "job");
			return "redirect:/jobs";

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

			model.addAttribute("eventCreation", "true");

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

		model.addAttribute("active", "event");
		return "editEventTemplate";
	}

	@RequestMapping(value = "/editAnEvent/{id}", method = RequestMethod.POST)
	public String editJobAPost(Model model, @PathVariable Long id, @RequestParam("name") String name,
			@RequestParam("date") String dateStr, @RequestParam("description") String description,
			@RequestParam("location") String location, @RequestParam("startTime") String startTime,

			@RequestParam("endTime") String endTime,
			@RequestParam(value = "public", required = false) boolean isPublic/*
																				 * , @ModelAttribute
																				 * (
																				 * "editEvent")
																				 * Event
																				 * editEvent
																				 */) {

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

			// User u = getCurrentUser();
			Event editEvent = getEventDao().getObjectById(id);

			editEvent.setName(name);
			editEvent.setDescription(description);
			editEvent.setLocation(location);
			// editEvent.setPoster(u);

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

				return "editEventTemplate";
			}

			//ERROR CHECK AFTER TIMES FAILED
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

				return "editEventTemplate";

			}

			editEvent.setDate(eventDate);
			editEvent.setStartTime(startTime);
			editEvent.setEndTime(endTime);

			if (isPublic) {
				editEvent.setToPublic(1);
			}

			try {
				getEventDao().updateEvent(editEvent);
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

			model.addAttribute("eventCreation", "true");

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
	@RequestMapping(value = "/eventsTemplate", method = RequestMethod.GET)
	public String events(Model model) {

		ArrayList<Event> events;
		try {
			events = getEventDao().getAll();
			sortEvents(events);
			model.addAttribute("events", events);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("active", "event");
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

		model.addAttribute("active", "event");
		return "eventSingle";

	}

	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public String deleteEvent(Model model, @ModelAttribute("currentEvent") Event currentEvent) {
		getEventDao().deleteEvent(currentEvent.getId());
		return "redirect:/eventsTemplate";
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

		ArrayList<Major> majorList = getMajorDao().getAll();
		ArrayList<Title> titleList = getTitleDao().getAll();

		model.addAttribute("majorList", majorList);
		model.addAttribute("titleList", titleList);

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
	public String registrationPost(Model model, @RequestParam("title") int title,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("suffix") String suffix, @RequestParam("benEmail") String benEmail,
			@RequestParam("personalEmail") String personalEmail,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "workPhone", required = false) String workPhone,
			@RequestParam("linkedin") String linkedin, @RequestParam("bio") String bio,
			@RequestParam("standing") int standing, @RequestParam("gradYear") int gradYear,
			@RequestParam("gradSchool") String gradSchool, @RequestParam("major") int major,
			@RequestParam("company") String company, @RequestParam("occupation") String occupation,
			@RequestParam("experience") String experience, @RequestParam("password") String password,
			@RequestParam("passConfirm") String passConfirm) {

		try {

			// Empty title = -1
			boolean validTitle = getTitleDao().getObjectById((long) title) != null || title == -1 ? true : false;

			// Size of first name on the database is 45
			boolean validFirstName = firstName != null && firstName.matches(".{2,44}") ? true : false;

			// Size of last name on the database is 45
			boolean validLastName = lastName != null && lastName.matches(".{2,44}") ? true : false;

			boolean validSuffix = suffix == null || suffix.equals("") || suffix.matches(".{2,}") ? true : false;

			boolean validPerEmail = personalEmail == null || personalEmail.equals("")
					|| personalEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@[a-zA-Z_0-9]+[.][a-zA-Z_0-9]{2,4}") ? true
							: false;

			boolean validBenEmail = benEmail != null && benEmail.matches("[a-zA-Z](?:[a-zA-Z_0-9])+@ben.edu") ? true
					: false;

			boolean validPhone = phone == null || phone.replace(" ", "").equals("")
					|| phone.trim().matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}") ? true : false;

			boolean validWorkPhone = workPhone == null || workPhone.replace(" ", "").equals("")
					|| workPhone.trim().matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}") ? true : false;

			// Linkedin length on the database is 200
			boolean validLinkedin = linkedin == null || linkedin.equals("")
					|| (linkedin.contains("linkedin.com/in/") && linkedin.length() < 199) ? true : false;

			// Bio length on the database is 1000
			boolean validBio = bio == null || bio.equals("") || bio.length() < 999 ? true : false;

			// 1 for student, 2 for alumni, 3 for faculty
			int role = standing == 1 || standing == 2 || standing == 3 ? standing : -1;

			// No grad year = 0
			boolean validGradYear = gradYear > 1900 || gradYear == 0 ? true : false;

			// Graduate school size on the database is 200
			boolean validGradSchool = gradSchool == null || gradSchool.equals("") || gradSchool.length() < 199 ? true
					: false;

			boolean validMajor = getMajorDao().getObjectById(major) != null ? true : false;

			// Company size on the database is 200
			boolean validCompany = company == null || company.equals("") || company.length() < 199;

			// Occupation size on the database is 200
			boolean validOccupation = occupation == null || occupation.equals("") || occupation.length() < 199;

			// Size of experience on the database is 1000
			boolean validExperience = experience == null || experience.matches("") || experience.length() < 999;

			boolean validPassword = password != null && password.matches(".{2,}") && password.length() < 300
					&& passConfirm != null && password.equals(passConfirm) ? true : false;

			if (validTitle && validFirstName && validLastName && validSuffix && validBenEmail && validPerEmail
					&& role != -1 && (gradYear != -1) && (role != -1) && validPhone && validWorkPhone && validLinkedin
					&& validBio && validGradYear && validGradSchool && validMajor && validCompany && validOccupation
					&& validExperience && validPassword) {

				firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
				lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);

				User register = new User();

				if (title != -1) {
					register.setTitleID(title);
				}

				register.setFirstName(firstName);
				register.setLastName(lastName);

				if (suffix != null && suffix.equals("")) {
					register.setSuffix(null);
				} else {
					register.setSuffix(suffix);
				}

				register.setEmail(benEmail);

				if (personalEmail != null && personalEmail.equals("")) {
					register.setPersonalEmail(null);
				} else {
					register.setPersonalEmail(personalEmail);
				}

				register.setRole(role);

				if (phone != null && phone.trim().matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")) {
					register.setPhoneNumber(phone.trim());
				}

				if (workPhone != null && workPhone.trim().matches("\\([0-9]{3}\\)[0-9]{3}-[0-9]{4}")) {
					register.setPhoneNumber(workPhone.trim());
				}

				if (linkedin != null && !linkedin.equals("")) {
					register.setSocialMedia(linkedin);
				}

				if (bio != null && bio.equals("")) {
					register.setBio(null);
				} else {
					register.setBio(bio);
				}

				if (gradYear != 0) {
					register.setGraduationYear(gradYear);
				}

				if (gradSchool != null && gradSchool.equals("")) {
					register.setGraduateSchool(null);
				} else {
					register.setGraduateSchool(gradSchool);
				}

				// TODO FIGURE OUT HOW TO SET THE MAJOR
				register.setMajor(null);

				if (company != null && company.equals("")) {
					register.setCompany(null);
				} else {
					register.setCompany(company);
				}

				if (occupation != null && occupation.equals("")) {
					register.setOccupation(null);
				} else {
					register.setOccupation(occupation);
				}

				if (experience != null && experience.equals("")) {
					register.setExperience(null);
				} else {
					register.setExperience(experience);
				}

				// FIX THE SALT
				register.setSalt(password);
				register.setPassword(pwEncoder.encode(password));

				try {
					getUserDao().addUser(register);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return "redirect:/";

			} else {
				HashMap<String, String> errors = new HashMap<String, String>();

				if (!validTitle) {
					errors.put("title", "Error in the input for title.");
				}

				if (!validFirstName) {
					errors.put("firstName", "Error in the input for first name.");
				}

				if (!validLastName) {
					errors.put("lastName", "Error in the input for last name.");
				}

				if (!validSuffix) {
					errors.put("suffix", "Error in the input for suffix.");
				}

				if (!validBenEmail) {
					errors.put("benEmail", "Error in the input for your Benedictine email.");
				}

				if (!validPerEmail) {
					errors.put("personalEmail", "Error in the input for your personal email.");
				}

				if (!validPhone) {
					errors.put("phone", "Error in the input for your phone number.");
				}

				if (!validWorkPhone) {
					errors.put("workPhone", "Error in the input for your work's phone number.");
				}

				if (!validLinkedin) {
					errors.put("linkedin", "Error in the input for the linkedin link.");
				}

				if (!validBio) {
					errors.put("phone", "Error in the input for your biography.");
				}

				if (role == -1) {
					errors.put("standing", "Error in the selection for standing.");
				}

				if (gradYear == -1) {
					errors.put("gradYear", "Error in the input for the graduation year.");
				}

				if (!validGradSchool) {
					errors.put("gradSchool", "Error in the input for graduate school.");
				}

				if (!validMajor) {
					errors.put("major", "Error in the selection of your major.");
				}

				if (!validCompany) {
					errors.put("company", "Error in the input for your company.");
				}

				if (!validOccupation) {
					errors.put("occupation", "Error in the input for your occupation.");
				}

				if (!validExperience) {
					errors.put("experience", "Error in the input for your professional experience.");
				}

				if (!validPassword) {
					errors.put("passwords", "Passwords either do not match or are too short.");
				}

				model.addAttribute("errors", errors);

				ArrayList<Major> majorList = getMajorDao().getAll();
				ArrayList<Title> titleList = getTitleDao().getAll();

				model.addAttribute("majorList", majorList);
				model.addAttribute("titleList", titleList);

				return "registration";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<Major> majorList = getMajorDao().getAllMajors();
		ArrayList<Title> titleList = getTitleDao().getAll();

		model.addAttribute("majorList", majorList);
		model.addAttribute("titleList", titleList);

		return "registration";
	}

	// @RequestMapping(value = "/massRegister", method = RequestMethod.POST)
	// public String massRegistration(Model model, HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestParam CommonsMultipartFile[] multiple) throws IOException,
	// SerialException, SQLException {
	//
	// MultipartHttpServletRequest multipartRequest =
	// (MultipartHttpServletRequest) request;
	// MultipartFile multipartFile = multipartRequest.getFile("multiple");
	//
	// UploadFile file = new UploadFile();
	// file.setFileName(multipartFile.getOriginalFilename());
	// // file.setNotes(ServletRequestUtils.getStringParameter(request,
	// // "notes"));
	// // file.setType(multipartFile.getContentType());
	// if (file != null) {
	// byte[] bytes = multipartFile.getBytes();
	// Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
	// file.setData((com.mysql.jdbc.Blob) blob);
	// getUserDao().addMultiple(file.getFileName());
	// }
	// // model.addAttribute("active", "index");
	// return "admin";
	// }

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
		ArrayList<Major> mi = getMajorDao().getAllMajors();
		ArrayList<Title> t = getTitleDao().getAll();
		ArrayList<Interest> i = getInterestDao().getAll();
		ArrayList<Interest> uI = getInterestDao().getAllByUser(profileUser);
		ArrayList<Interest> interests = new ArrayList<Interest>();

		Boolean exists = false;

		for (Interest interest : i) {
			for (Interest userInterest : uI) {
				if (userInterest.getName().equals(interest.getName())) {
					exists = true;
				}
			}
			if (!exists) {
				interests.add(interest);
			}
			exists = false;
		}

		model.addAttribute("currUser", u);
		model.addAttribute("profileUser", profileUser);
		model.addAttribute("majors", m);
		model.addAttribute("minors", mi);
		model.addAttribute("titles", t);
		model.addAttribute("title", title);
		model.addAttribute("interests", interests);
		model.addAttribute("userInterests", uI);

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
	public String edit(Model model, @PathVariable Long id, @RequestParam("title") String title,
			@RequestParam("fName") String firstName, @RequestParam("lName") String lastName,
			@RequestParam("suffix") String suffix, @RequestParam("personalEmail") String personalEmail,
			@RequestParam("graduationYear") String graduationYear, @RequestParam("major") String major,
			@RequestParam("doubleMajor") String doubleMajor, @RequestParam("thirdMajor") String thirdMajor,
			@RequestParam("occupation") String occupation, @RequestParam("biography") String biography,
			@RequestParam("experience") String experience, @RequestParam("interests") ArrayList<String> interests,
			@RequestParam("minor") String minor, @RequestParam("secondMinor") String secondMinor,
			@RequestParam("thirdMinor") String thirdMinor, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword/*
																	 * , @ModelAttribute
																	 * (
																	 * "profileUser")
																	 * User
																	 * profileUser,
																	 * 
																	 * @RequestParam
																	 * (
																	 * "resume")
																	 * MultipartFile
																	 * resume, @RequestParam
																	 * (
																	 * "profile")
																	 * MultipartFile
																	 * profile
																	 */) throws IOException {

		// VALIDATION
		if (validateEditFormSubmission(password, confirmPassword, firstName, lastName, personalEmail)
				&& Validator.validatePassword(password, false)) {

			// System.out.println(profileUser.getFirstName());
			User profileUser = getUserDao().getObjectById(id);

			// User profileUser = getCurrentUser();

			if (Validator.validateSelect(graduationYear)) {
				profileUser.setGraduationYear((Integer.parseInt(graduationYear)));
			}
			if (!major.equals("Select") && !getMajorDao().getByName(major).equals(null)
					&& getMajorDao().getByName(major) != null) {
				profileUser.addMajor(getMajorDao().getByName(major));
			}
			if (!doubleMajor.equals("Select") && !getMajorDao().getByName(doubleMajor).equals(null)
					&& getMajorDao().getByName(doubleMajor) != null) {
				profileUser.addMajor(getMajorDao().getByName(doubleMajor));
			}
			if (!thirdMajor.equals("Select") && !getMajorDao().getByName(thirdMajor).equals(null)
					&& getMajorDao().getByName(thirdMajor) != null) {
				profileUser.addMajor(getMajorDao().getByName(thirdMajor));
			}

			if (!minor.equals("Select") && !getMajorDao().getByName(minor).equals(null)
					&& getMajorDao().getByName(minor) != null) {
				profileUser.addMinor(getMajorDao().getByName(minor));
			}
			if (!secondMinor.equals("Select") && !getMajorDao().getByName(secondMinor).equals(null)
					&& getMajorDao().getByName(secondMinor) != null) {
				profileUser.addMinor(getMajorDao().getByName(secondMinor));
			}
			if (!thirdMinor.equals("Select") && !getMajorDao().getByName(thirdMinor).equals(null)
					&& getMajorDao().getByName(thirdMinor) != null) {
				profileUser.addMinor(getMajorDao().getByName(thirdMinor));
			}

			if (!title.equals("Select") && !getTitleDao().getObjectByName(title).equals(null)) {
				profileUser.setTitleID(getTitleDao().getObjectByName(title).getId());
			}
			//
			if (!Validator.validateSelect(graduationYear)) {
				profileUser.setGraduationYear(0);
			} else {
				profileUser.setGraduationYear(Integer.parseInt(graduationYear));
			}

			// u.setTitle(title);
			profileUser.setFirstName(firstName);
			profileUser.setLastName(lastName);
			profileUser.setSuffix(suffix);
			profileUser.setPersonalEmail(personalEmail);
			profileUser.setOccupation(occupation);
			profileUser.setBio(biography);
			profileUser.setExperience(experience);

			try {
				getUserDao().updateUser(profileUser);
				getMajorDao().updateMajorsByUser(profileUser);
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

			// TODO PLACE FILE HERE

			getInterestDao().clearUserInterest(profileUser);
			profileUser.clearInterest();

			for (String i : interests) {
				try {
					Interest interest = getInterestDao().getByName(i);
					getInterestDao().addInterestToUser(profileUser, interest);
					profileUser.addInterest(interest);
				} catch (Exception e) {

				}
			}

			if (validatePassword(password, confirmPassword)) {
				profileUser.setPassword(pwEncoder.encode(password));
			}

			try {
				getUserDao().updateUser(profileUser);
				getMajorDao().updateMajorsByUser(profileUser);
			} catch (Exception e) {

			}

			return "redirect:/user/" + profileUser.getId();
		}

		HashMap<String, String> e = new HashMap<String, String>();// TODO

		ArrayList<Major> m = getMajorDao().getAllMajors();

		// u = getCurrentUser();
		User profileUser = getUserDao().getObjectById(id);

		// u.setMajor(getMajorDao().getMajorByUser(u));
		// u.setConcentration(getMajorDao().getConcentrationByUser(u));
		// u.setMinor(getMajorDao().getMinorByUser(u));

		profileUser.setMajor(getMajorDao().getMajorByUser(profileUser));
		profileUser.setConcentration(getMajorDao().getConcentrationByUser(profileUser));
		profileUser.setMinor(getMajorDao().getMinorByUser(profileUser));

		// Change this to a u.setTitle(); --> Refactor TitleId To a String

		Title t = getTitleDao().getObjectById(profileUser.getTitleID());

		ArrayList<Major> ma = getMajorDao().getAllMajors();
		ArrayList<Major> mi = getMajorDao().getAllMajors();
		ArrayList<Title> titles = getTitleDao().getAll();
		ArrayList<Interest> i = getInterestDao().getAll();
		ArrayList<Interest> uI = getInterestDao().getAllByUser(profileUser);
		ArrayList<Interest> interests2 = new ArrayList<Interest>();

		Boolean exists = false;

		for (Interest interest : i) {
			for (Interest userInterest : uI) {
				if (userInterest.getName().equals(interest.getName())) {
					exists = true;
				}
			}
			if (!exists) {
				interests2.add(interest);
			}
			exists = false;
		}

		// For User Error Checking
		HashMap<String, String> error = new HashMap<String, String>();

		if (!Validator.validatePasswordsMatch(password, confirmPassword))
			error.put("password", "Passwords Must Match!");
		else if (!Validator.isNull(password) && !Validator.validatePassword(password, false))
			error.put("password", "Password Is Not Long Enough!");// TODO Change
																	// when
																	// regex
																	// does

		if (!Validator.validateName(firstName))
			error.put("fName", "Invalid First Name");
		if (!Validator.validateName(lastName))
			error.put("fName", "Invalid Last Name");
		if (!Validator.validateEmail(personalEmail, false))
			error.put("fName", "Invalid Email Address");

		getInterestDao().clearUserInterest(profileUser);
		profileUser.clearInterest();

		for (String newInterest : interests) {
			try {
				Interest interest = getInterestDao().getByName(newInterest);
				getInterestDao().addInterestToUser(profileUser, interest);
				profileUser.addInterest(interest);
			} catch (Exception ex) {

			}
		}

		profileUser.setSuffix(suffix);
		profileUser.setOccupation(occupation);
		profileUser.setBio(biography);
		profileUser.setExperience(experience);

		model.addAttribute("user", profileUser);
		model.addAttribute("majors", ma);
		model.addAttribute("minors", mi);
		model.addAttribute("titles", titles);
		model.addAttribute("title", t);
		model.addAttribute("interests", interests2);
		model.addAttribute("userInterests", uI);
		model.addAttribute("errors", error);
		return "edit";

	}

	@RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
	public String jobsSingle(Model model, @PathVariable Long id) {

		try {

			Job currentJob = getJobDao().getObjectById(id);
			model.addAttribute("currentJob", currentJob);

		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("active", "job");
		return "jobsSingleTemplate";

	}

	@RequestMapping(value = "/deleteJob", method = RequestMethod.POST)
	public String deleteJob(Model model, @ModelAttribute("currentJob") Job currentJob) {
		getJobDao().deleteJob(currentJob.getId());
		return "redirect:/jobs";
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
			model.addAttribute("alumniCount", alumni.size());
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
			model.addAttribute("listCount", allUser.size());
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
		return "redirect:/allUsers";
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

		return (Validator.validatePasswordsMatch(password, confirmPassword)
				&& Validator.validatePassword(password, true) && Validator.validateName(firstName)
				&& Validator.validateName(lastName) && Validator.validateGraduationYear(graduationYear, false)
				&& Validator.validateEmail(personalEmail, false));
	}

	private boolean validatePassword(String password, String confirmPassword) {

		return (Validator.validatePasswordsMatch(password, confirmPassword)
				&& Validator.validatePassword(password, true));
	}

	private boolean validateEditFormSubmission(String password, String confirmPassword, String firstName,
			String lastName, String personalEmail) {

		return (Validator.validatePasswordsMatch(password, confirmPassword)
				// && Validator.validatePassword(password, false)
				&& Validator.validateName(firstName) && Validator.validateName(lastName)
				&& Validator.validateEmail(personalEmail, false));
	}
}