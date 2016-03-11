package edu.ben.template.model;

/**
 * Class that contains all attributes of a Department.
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class Department {

	private Long id;
	private Long collegeId;
	private String name;

	/**
	 * Empty constructor.
	 */
	public Department() {
		super();
	}

	/**
	 * Constructor that consist of the all the attributes of a department.
	 * 
	 * @param collegeId
	 * @param name
	 */
	public Department(Long collegeId, String name) {
		super();
		this.collegeId = collegeId;
		this.name = name;
	}

	/**
	 * 
	 * @return the Id of the department.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the Id of the department.
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the Id of the college the user came from.
	 */
	public Long getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the Id of what college they came from.
	 * 
	 * @param collegeId
	 */
	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * 
	 * @return the name of the department.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the department.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ToString that prints out the Department in String Form.
	 */
	@Override
	public String toString() {
		return "Department [id=" + id + ", collegeId=" + collegeId + ", name=" + name + "]";
	}

}
