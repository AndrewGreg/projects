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

	public Testimonial(long id, String testimonial, User poster) {
		this.id = id;
		this.testimonial = testimonial;
		this.poster = poster;
	}

	public Testimonial(String testimonial, User poster) {
		this.testimonial = testimonial;
		this.poster = poster;
	}

	public long getId() {
		return id;
	}

	public String getTestimonial() {
		return testimonial;
	}

	public User getPoster() {
		return poster;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTestimonial(String testimonial) {
		this.testimonial = testimonial;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

}
