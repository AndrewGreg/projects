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
	private boolean salary;
	private int start_salary;
	private int end_salary;
	private float start_wage;
	private float end_wage;
	private String reference;
	private int toPublic;
	private int hours;
	private String link;
	// private String endDate;
	// private String startDate;

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
	 * @param start_salary
	 * @param end_salary
	 * @param start_wage
	 * @param end_wage
	 * @param reference
	 * @param toPublic
	 * @param hours
	 * @param link
	 */
	public Job(long id, String name, String description, String company, User poster, String location, boolean salary,
			int start_salary, int end_salary, float start_wage, float end_wage, String reference, int toPublic,
			int hours, String link) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.company = company;
		this.poster = poster;
		this.location = location;
		this.salary = salary;
		this.start_salary = start_salary;
		this.end_salary = end_salary;
		this.start_wage = start_wage;
		this.end_wage = end_wage;
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
	public boolean getSalary() {
		return salary;
	}

	/**
	 * Sets the salary of the job.
	 * 
	 * @param salary
	 */
	public void setSalary(boolean salary) {
		this.salary = salary;
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
	public int isToPublic() {
		return toPublic;
	}

	/**
	 * Set the job to the public or not.
	 * 
	 * @param toPublic
	 */
	public void setToPublic(int toPublic) {
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
	 * 
	 * @return starting Salary of position.
	 */
	public int getStart_salary() {
		return start_salary;
	}

	/**
	 * Set the starting salary cap.
	 * 
	 * @param start_salary
	 */
	public void setStart_salary(int start_salary) {
		this.start_salary = start_salary;
	}

	/**
	 * 
	 * @return the ending salary cap.
	 */
	public int getEnd_salary() {
		return end_salary;
	}

	/**
	 * Set the ending salary.
	 * 
	 * @param end_salary
	 */
	public void setEnd_salary(int end_salary) {
		this.end_salary = end_salary;
	}

	/**
	 * 
	 * @return the starting wage of position. (intern)
	 */
	public float getStart_wage() {
		return start_wage;
	}

	/**
	 * Set starting wage for position.
	 * 
	 * @param start_wage
	 */
	public void setStart_wage(float start_wage) {
		this.start_wage = start_wage;
	}

	/**
	 * 
	 * @return the ending wage for position.
	 */
	public float getEnd_wage() {
		return end_wage;
	}

	/**
	 * Set ending wage of position.
	 * 
	 * @param end_wage
	 */
	public void setEnd_wage(float end_wage) {
		this.end_wage = end_wage;
	}

	/**
	 * 
	 * @return 1 if the job is displayed to the public.
	 */
	public int getToPublic() {
		return toPublic;
	}

	/**
	 * ToString of the job class.
	 */
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", description=" + description + ", company=" + company
				+ ", poster=" + poster + ", location=" + location + ", salary=" + salary + ", start_salary="
				+ start_salary + ", end_salary=" + end_salary + ", start_wage=" + start_wage + ", end_wage=" + end_wage
				+ ", reference=" + reference + ", toPublic=" + toPublic + ", hours=" + hours + ", link=" + link + "]";
	}

}
