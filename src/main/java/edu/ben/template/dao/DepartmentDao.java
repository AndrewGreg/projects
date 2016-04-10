package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import edu.ben.template.model.Department;

/**
 * This Dao controls the object class of Department
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class DepartmentDao extends BaseDao<Department> {

	static final String SEARCH = "SELECT * FROM ";

	/**
	 * Super constructor
	 */
	public DepartmentDao() {
		super();
	}

	/**
	 * Grab the Department object by Id. We use this one.
	 * 
	 * @param departmentId
	 *            is type long
	 * @return the object by id.
	 */
	public Department getObjectById(long departmentId) {
		return this.getObjectById(departmentId, false);
	}

	/**
	 * Grabs the Department object by it's Id depending on the boolean type. We
	 * don't really use this one.
	 * 
	 * @param departmentId
	 * @param complete
	 * @return the Id of the object.
	 */
	public Department getObjectById(long departmentId, boolean complete) {
		if (departmentId == 0) {
			/* Probably want to log this */
			return null;
		}
		Department object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = SEARCH + "Department WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { departmentId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	/**
	 * Retrieves all Departments from database.
	 * 
	 * @return all departments.
	 */
	public ArrayList<Department> getAll() {

		List<Department> departments = new ArrayList<Department>();
		String sql = SEARCH + "Department ORDER BY name";

		try {
			departments = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Department>) departments;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Inserts the Department into the database.
	 * 
	 * @param Department
	 *            entails of name and college id.
	 */
	public void addDepartment(Department Department) {

		String sql = "INSERT INTO Department (name, college_id) VALUES (?, ?)";

		jdbcTemplate.update(sql, new Object[] { Department.getName(), Department.getCollegeId() });

		return;
	}

	/**
	 * Enables the user to edit the following attributes.
	 * 
	 * @param Department being updated.
	 */
	public void updateDepartment(Department Department) {

		String sql = "UPDATE Department SET name = ?, college_id = ? WHERE Department.id = ?";
		try {
			jdbcTemplate.update(sql, Department.getName(), Department.getCollegeId(), Department.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;

	}

	/**
	 * Searches by name of the Department in ascending order.
	 * 
	 * @param Department
	 * 
	 * @return the name of the Department.
	 */
	public ArrayList<Department> getSearchByDepartmentName(String name) {

		List<Department> departments = new ArrayList<Department>();
		String sql = "SELECT name FROM Department WHERE name LIKE '%name%' ORDER BY name";

		try {
			// Test this.
			departments = jdbcTemplate.query(sql, new Object[] { name }, getRowMapper());

			return (ArrayList<Department>) departments;
		} catch (EmptyResultDataAccessException e) {
		
			return null;
		}
	}

	/**
	 * Row Mapper.
	 */
	@Override
	public RowMapper<Department> getRowMapper() {
		return new RowMapper<Department>() {
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Department department = new Department();
				department.setId(rs.getLong("id"));
				department.setName(rs.getString("name"));
				department.setCollegeId(rs.getLong("college_id"));

				return department;
			}
		};
	}

	/**
	 * Creates the connection to the database.
	 */
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Department department) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into department (id, name) values (?,?) "
						+ "on duplicate key update name = values(name)", new String[] { "id" });
				ps.setLong(1, department.getId());
				ps.setString(2, department.getName());
				return ps;
			}
		};
	}
}