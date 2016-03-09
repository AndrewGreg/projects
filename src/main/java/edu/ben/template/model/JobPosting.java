package edu.ben.template.model;

public class JobPosting {

	private long id;
	private String name;
	private String description;
	private String company;
	private User poster;

	public JobPosting(){
		
	}
	

	public JobPosting(String name, String description, String company) {
		super();
		this.name = name;
		this.description = description;
		this.company = company;
	}


	public JobPosting(String name, String description, String company, User poster) {
		super();
		this.name = name;
		this.description = description;
		this.company = company;
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

}
