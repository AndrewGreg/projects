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

	/**
	 * Empty constructor.
	 */
	public College() {
		super();
	}

	/**
	 * Constructor that defines a College.
	 * 
	 * @param name
	 */
	public College(String name) {
		super();

		this.name = name;
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
	 * Prints the College in a string form.
	 */
	@Override
	public String toString() {
		return "College [id=" + id + ", name=" + name + "]";
	}

}
