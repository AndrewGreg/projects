package edu.ben.template.model;

/**
 * Class that defines a Job.
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class Job {

	private long id;
	private String name;
	private String description;
	private String company;
	private User poster;
	private String location;
	private double salary;
	private String position;
	private String endDate;
	private String startDate;
	private String reference;
	private boolean toPublic;
	private int hours;
	private String link;

	/**
	 * Empty constructor
	 */
	public Job() {
		super();
	}

	/**
	 * Constructor passing the following attributes.
	 * 
	 * @param name
	 * @param description
	 * @param company
	 */
	public Job(String name, String description, String company) {
		super();
		this.name = name;
		this.description = description;
		this.company = company;
	}

	/**
	 * Constructor passing basic variables of a job.
	 * 
	 * @param name
	 * @param description
	 * @param company
	 * @param poster
	 */
	public Job(String name, String description, String company, User poster) {
		super();
		this.name = name;
		this.description = description;
		this.company = company;
		this.poster = poster;
	}

	/**
	 * Constructor that passes in all the attributes of the job class.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param company
	 * @param poster
	 * @param location
	 * @param salary
	 * @param position
	 * @param endDate
	 * @param startDate
	 * @param reference
	 * @param toPublic
	 * @param hours
	 * @param link
	 */
	public Job(long id, String name, String description, String company, User poster, String location, double salary,
			String position, String endDate, String startDate, String reference, boolean toPublic, int hours,
			String link) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.company = company;
		this.poster = poster;
		this.location = location;
		this.salary = salary;
		this.position = position;
		this.endDate = endDate;
		this.startDate = startDate;
		this.reference = reference;
		this.toPublic = toPublic;
		this.hours = hours;
		this.link = link;
	}

	/**
	 * 
	 * @return the id of Job.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of the job.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the name of the job.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the job.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * 
	 * @return the description of the job.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the job.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return the name of the company.
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Sets the name of the company.
	 * 
	 * @param company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * 
	 * @return the user who posted the job.
	 */
	public User getPoster() {
		return poster;
	}

	/**
	 * Sets the name of the user who posted the job.
	 * 
	 * @param poster
	 */
	public void setPoster(User poster) {
		this.poster = poster;
	}

	/**
	 * @return where the job is located at.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the job.
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @return the salary of the job.
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Sets the salary of the job.
	 * 
	 * @param salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * 
	 * @return the type of position of the job (Full-Time/Part-Time).
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Sets the position of the job.
	 * 
	 * @param position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 
	 * @return The end date before the job being displayed is taken off.
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the last day for the job to be displayed.
	 * 
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 
	 * @return the Start date of the job.
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Set the start date of the job.
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 
	 * @return how you heard about this job.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Set the reference of the job.
	 * 
	 * @param reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * 
	 * @return true, if the job is available to the public.
	 */
	public boolean isToPublic() {
		return toPublic;
	}

	/**
	 * Set the job to the public or not.
	 * 
	 * @param toPublic
	 */
	public void setToPublic(boolean toPublic) {
		this.toPublic = toPublic;
	}

	/**
	 * 
	 * @return the hours of the job.
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * Set the hours of the job.
	 * 
	 * @param hours
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * 
	 * @return the link that redirects to the application.
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Set the link to gain access to job application.
	 * 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * ToString of the job class.
	 */
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", description=" + description + ", company=" + company
				+ ", poster=" + poster + ", location=" + location + ", salary=" + salary + ", position=" + position
				+ ", endDate=" + endDate + ", startDate=" + startDate + ", reference=" + reference + ", toPublic="
				+ toPublic + ", hours=" + hours + ", link=" + link + "]";
	}
}
