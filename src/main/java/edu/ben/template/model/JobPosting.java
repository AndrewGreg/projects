package edu.ben.template.model;

public class JobPosting {

	private long id;
	private String name;
	private String description;
	private String company;
	private String location;
	private String salary;
	private User poster;

	public JobPosting(){
		
	}
	

	public JobPosting(String name, String description, String company, String location, String salary) {
		super();
		this.name = name;
		this.description = description;
		this.company = company;
		this.location = location;
		this.salary = salary;
	}


	public JobPosting(String name, String description, String company, String location, String salary, User poster) {
		super();
		this.name = name;
		this.description = description;
		this.company = company;
		this.location = location;
		this.salary = salary;
		this.poster = poster;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getSalary() {
		return salary;
	}


	public void setSalary(String salary) {
		this.salary = salary;
	}

}
