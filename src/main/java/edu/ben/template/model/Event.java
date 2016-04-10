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
	private int toPublic;
	private float longitude;
	private float latitude;
	private int role;
	private String reference;
	private String location;
	private String startTime;
	private String endTime;

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
	 * Constructor with basic info of an event along with the poster and times.
	 * 
	 * @param name
	 * @param date
	 * @param description
	 * @param poster
	 * @param startTime
	 * @param endTime
	 */
	public Event(String name, Date date, String description, User poster, String startTime, String endTime) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.poster = poster;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Constructor with all variables that are used with an Event.
	 * 
	 * @param name
	 * @param date
	 * @param description
	 * @param poster
	 * @param toPublic
	 * @param longitude
	 * @param latitude
	 * @param role
	 * @param reference
	 */
	public Event(String name, Date date, String description, User poster, int toPublic, float longitude, float latitude,
			int role, String reference, String location) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.poster = poster;
		this.toPublic = toPublic;
		this.longitude = longitude;
		this.latitude = latitude;
		this.role = role;
		this.reference = reference;
		this.setLocation(location);
	}

	/**
	 * Gets the id of the event
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
	 * Gets the name of the event
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
	 * Gets the date of the event
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
	 * Gets the description of the event
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
	 * Gets the poster of the event
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
	 * (Google Maps)
	 * 
	 * @return the longitude of the location.
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude of the location.
	 * 
	 * @param longitude
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/**
	 * (Google Maps)
	 * 
	 * @return the latitude of the location.
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude of the location.
	 * 
	 * @param latitude
	 */
	public void setLatitude(float latitude) {
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
	 * Returns 1 if it is a public event
	 * 
	 * @return 1, if the event is displayed to everyone.
	 */
	public int getToPublic() {
		return toPublic;
	}

	/**
	 * Set the number for the event (security).
	 * 
	 * @param toPublic
	 */
	public void setToPublic(int toPublic) {
		this.toPublic = toPublic;
	}

	/**
	 * Returns the reference number to the event
	 * 
	 * @return reference number to the event.
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Set the reference number to the event.
	 * 
	 * @param reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the location of the event
	 * 
	 * @return the location of the Event.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Set the location of the event.
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Returns the event start time
	 * 
	 * @return startTime of the event
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time for the event
	 * 
	 * @param startTime
	 *            of the event
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Returns the end time of the event
	 * 
	 * @return endTime of the event
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time for the event
	 * 
	 * @param endTime
	 *            of the event
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Returns the time range for the event
	 * 
	 * @return range between start time and end time (startTime - endTime)
	 */
	public String getTimeRange() {
		return getStartTime() + " - " + getEndTime();
	}

	/**
	 * Returns the month that the event is taking place on
	 * 
	 * @return month
	 */
	public String getEventMonth() {
		String month = "";
		if (date != null) {
			int monthSpot = 1;

			String[] dateParts = date.toString().split("-");

			switch (dateParts[monthSpot]) {
			case "01":
				month = "JAN";
				break;
			case "02":
				month = "FEB";
				break;
			case "03":
				month = "MAR";
				break;
			case "04":
				month = "APR";
				break;
			case "05":
				month = "MAY";
				break;
			case "06":
				month = "JUN";
				break;
			case "07":
				month = "JUL";
				break;
			case "08":
				month = "AUG";
				break;
			case "09":
				month = "SEP";
				break;
			case "10":
				month = "OCT";
				break;
			case "11":
				month = "NOV";
				break;
			case "12":
				month = "DEC";
				break;
			default:
				month = "";
				break;
			}
		}
		return month;
	}

	/**
	 * Returns the day the event will take place on
	 * 
	 * @return event day
	 */
	public String getEventDay() {
		return date != null && date.toString().split("-")[2] != null ? date.toString().split("-")[2] : null;
	}

	/**
	 * Returns a shortened description for display on the front page.
	 * 
	 * @return shortDescription shortened description
	 */
	public String getShortDescription() {
		String shortDescription = "";

		if (description.length() > 60 && description.length() > 0) {
			shortDescription = description.substring(0, 60) + "...";
			return shortDescription;
		} else {
			return description;
		}
	}

	/**
	 * Returns a link that takes you to the search in google maps for the
	 * location.
	 * 
	 * @return link search in google maps
	 */
	public String getGoogleMapsLink() {
		// https://maps.google.com/?q=term
		if (location != null) {
			return "https://maps.google.com/?q=" + location.trim().replace(" ", "+");
		} else {
			return "#";
		}
	}

	/**
	 * Prints the Event in String Form.
	 */
	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", date=" + date + ", description=" + description + ", poster="
				+ poster + ", toPublic=" + toPublic + ", longitude=" + longitude + ", latitude=" + latitude + ", role="
				+ role + ", reference=" + reference + ", location=" + location + "]";
	}
}
