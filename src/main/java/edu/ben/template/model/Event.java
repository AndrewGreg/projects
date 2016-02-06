package edu.ben.template.model;

import java.sql.Date;

public class Event {

	private Long id;
	private String name;
	private Date date;
	private String description;
	private User poster;

	public Event(){
		
	}
	
	public Event(String name, Date date, String description) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
	}

	public Event(String name, Date date, String description, User poster) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.poster = poster;
	}

	public long getId() {
		return id;
	}

	public void setId(long l) {
		this.id = l;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

}
