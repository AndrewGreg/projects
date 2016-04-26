package edu.ben.template.model;

/**
 * Class defining a User Testimonial
 * 
 * @author Jose E. Dieck
 * @version 19/04/2016
 *
 */
public class Testimonial {

	private long id;
	private String testimonial;
	private User poster;

	/**
	 * Empty constructor
	 */
	public Testimonial() {
		super();
	}

	/**
	 * Constructor to create a testimonial with all the required information
	 * 
	 * @param id
	 *            identifier for the testimonial
	 * @param testimonial
	 *            user inputed text
	 * @param poster
	 *            user that posted the testimonial
	 */
	public Testimonial(long id, String testimonial, User poster) {
		this.id = id;
		this.testimonial = testimonial;
		this.poster = poster;
	}

	/**
	 * Constructor to create a testimonial created by the user
	 * 
	 * @param testimonial
	 *            user inputed text
	 * @param poster
	 *            user that posted the testimonial
	 */
	public Testimonial(String testimonial, User poster) {
		this.testimonial = testimonial;
		this.poster = poster;
	}

	/**
	 * Method to get the testimonial id
	 * 
	 * @return id identifier for the testimonial
	 */
	public long getId() {
		return id;
	}

	/**
	 * Method to get the testimonial text
	 * 
	 * @return testimonial text that was inputed by the user
	 */
	public String getTestimonial() {
		return testimonial;
	}

	/**
	 * Method to get the user who posted the testimonial
	 * 
	 * @return poster user object
	 */
	public User getPoster() {
		return poster;
	}

	/**
	 * Method that sets the id for the testimoinal
	 * 
	 * @param id
	 *            identifier for the testimonial
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Method that sets the testimonial
	 * 
	 * @param testimonial
	 *            text input from the user
	 */
	public void setTestimonial(String testimonial) {
		this.testimonial = testimonial;
	}

	/**
	 * Method that sets the user who posted the testimonial
	 * 
	 * @param poster
	 *            user that created the testimonial
	 */
	public void setPoster(User poster) {
		this.poster = poster;
	}

	/**
	 * Method that returns a shortened version of the testimonial for display on
	 * the home page.
	 * 
	 * @return testimonial shortened to fit the display on the home page.
	 */
	public String getShortDescription() {

		if (testimonial.length() > 250) {
			return testimonial.substring(0, 247) + "...";
		} else {
			return testimonial;
		}
	}

}
