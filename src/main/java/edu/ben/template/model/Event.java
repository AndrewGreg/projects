package edu.ben.template.model;

import java.sql.Date;

/**
 * Class that contains all the attributes of an Event.
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class Event {

	private Long id;
	private String name;
	private Date date;
	private String description;
	private User poster;
	private int startTime;
	private int endTime;
	private int longitude;
	private int latitude;
	private int role;

	/**
	 * An empty constructor.
	 */
	public Event() {
		super();
	}

	/**
	 * Constructor with info about the event.
	 * 
	 * @param name
	 * @param date
	 * @param description
	 */
	public Event(String name, Date date, String description) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
	}

	/**
	 * Constructor with basic info of an event along with the poster.
	 * 
	 * @param name
	 * @param date
	 * @param description
	 * @param poster
	 */
	public Event(String name, Date date, String description, User poster) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.poster = poster;
	}

	/**
	 * Constructor with all variables that are used with an Event.
	 * 
	 * @param id
	 * @param name
	 * @param date
	 * @param description
	 * @param poster
	 * @param startTime
	 * @param endTime
	 * @param longitude
	 * @param latitude
	 * @param departmentId
	 */
	public Event(Long id, String name, Date date, String description, User poster, int startTime, int endTime,
			int longitude, int latitude, int role) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.poster = poster;
		this.startTime = startTime;
		this.endTime = endTime;
		this.longitude = longitude;
		this.latitude = latitude;
		this.role = role;
	}

	/**
	 * 
	 * @return the id of event.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id of the event.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the name of the event.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the event.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the date of the event.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date of the event.
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 
	 * 
	 * @return the description of the event.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the event.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return the user who posted the event.
	 */
	public User getPoster() {
		return poster;
	}

	/**
	 * Sets the name of the user who posted the event.
	 * 
	 * @param poster
	 */
	public void setPoster(User poster) {
		this.poster = poster;
	}

	/**
	 * 
	 * @return the start time of the event.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time of the event.
	 * 
	 * @param startTime
	 */
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return the end time of the event.
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time of the event.
	 * 
	 * @param endTime
	 */
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	/**
	 * (Google Maps)
	 * 
	 * @return the longitude of the location.
	 */
	public int getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude of the location.
	 * 
	 * @param longitude
	 */
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	/**
	 * (Google Maps)
	 * 
	 * @return the latitude of the location.
	 */
	public int getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude of the location.
	 * 
	 * @param latitude
	 */
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	/**
	 * (Google Maps)
	 * 
	 * @return the latitude of the location.
	 */
	public int getRole() {
		return role;
	}

	/**
	 * Sets the latitude of the location.
	 * 
	 * @param latitude
	 */
	public void setRole(int role) {
		this.role = role;
	}

	/**
	 * ToString Method of the Event class.
	 */
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description + ", poster="
				+ poster + ", startTime=" + startTime + ", endTime=" + endTime + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", role=" + role + "]";
	}

}
