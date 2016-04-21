package edu.ben.template.model;

/**
 * Model That includes the information of a RSVP.
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class Rsvp {

	private Long id;
	private Event eventId;
	private User userId;
	

	/**
	 * Empty Constructor
	 */
	public Rsvp() {
		super();
	}

	/**
	 * Constructor that passes in the first name, last name, and role of the
	 * person that Rsvp.
	 * 
	 * @param userId
	 * @param eventId
	 */
	public Rsvp(User userId,Event eventId) {
		super();
		this.userId = userId;
		this.eventId = eventId;

	}

	public Event getEventId() {
		return eventId;
	}

	public void setEventId(Event eventId) {
		this.eventId = eventId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	
	/**
	 * To String 
	 */
	@Override
	public String toString() {
		return "Rsvp [id=" + id + ", eventId=" + eventId + ", userId=" + userId + "]";
	}
	}
