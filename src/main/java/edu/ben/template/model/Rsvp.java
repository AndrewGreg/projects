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
	private String firstName;
	private String lastName;
	private String email;
	private String role;

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
	 * @param firstName
	 *            of attendant
	 * @param lastName
	 *            of attendant
	 * @param role
	 *            of attendant
	 */
	public Rsvp(String firstName, String lastName, String email, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * To String Method.
	 */
	@Override
	public String toString() {
		return "Rsvp [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + "]";
	}

}
