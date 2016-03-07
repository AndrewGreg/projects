package edu.ben.template.model;

/**
 * Class that defines a College.
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class College {

	private Long id;
	private String name;
	private String location;

	/**
	 * Empty constructor.
	 */
	public College() {
		super();
	}

	/**
	 * Constructor that defines a College.
	 * 
	 * @param id
	 * @param name
	 * @param location
	 */
	public College(Long id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	/**
	 * 
	 * @return the id of that college.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id for that college.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the name of the college.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the College.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return where that college is located at.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location of the college.
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Prints the College in a string form.
	 */
	@Override
	public String toString() {
		return "College [id=" + id + ", name=" + name + ", location=" + location + "]";
	}

}
