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

import edu.ben.template.model.College;

/**
 * This Dao controls the object class of College
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class CollegeDao extends BaseDao<College> {

	static final String SEARCH = "SELECT * FROM";

	/**
	 * Super constructor
	 */
	public CollegeDao() {
		super();
	}

	/**
	 * Grab the College object by Id. We use this one.
	 * 
	 * @param collegeId
	 *            is type long
	 * @return the object by id.
	 */
	public College getObjectById(long collegeId) {
		return this.getObjectById(collegeId, false);
	}

	/**
	 * Grabs the College object by it's Id depending on the boolean type. We
	 * don't really use this one.
	 * 
	 * @param collegeId
	 * @param complete
	 * @return the Id of the object.
	 */
	public College getObjectById(long collegeId, boolean complete) {
		if (collegeId == 0) {
			/* Probably want to log this */
			return null;
		}
		College object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = SEARCH + "college WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { collegeId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	/**
	 * Retrieves all Colleges from database.
	 * 
	 * @return all colleges.
	 */
	public ArrayList<College> getAll() {

		List<College> events = new ArrayList<College>();
		String sql = SEARCH + "college ORDER BY college";

		try {
			events = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<College>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Inserts the College into the database.
	 * 
	 * @param College
	 *            entails of name, description, company, the poster, and more.
	 */
	public void addDepartment(College college) {

		String sql = "INSERT INTO college (name, college_id) VALUES (?, ?)";

		jdbcTemplate.update(sql, new Object[] { college.getName(), college.getId() });

		return;
	}

	/**
	 * Enables the user to edit the following attributes.
	 * 
	 * @param College
	 */
	public void updateDepartment(College college) {

		String sql = "UPDATE college SET name = ?, college_id = ? WHERE college.id = ?";
		try {
			jdbcTemplate.update(sql, college.getName(), college.getId(), college.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;

	}

	/**
	 * Searches by name of the College in ascending order.
	 * 
	 * @param College
	 * 
	 * @return the name of the College.
	 */
	public ArrayList<College> getSearchByDepartmentName(String name) {

		List<College> colleges = new ArrayList<College>();
		String sql = "SELECT name FROM college WHERE name LIKE '%name%' ORDER BY name";

		try {
			// Test this.
			colleges = jdbcTemplate.query(sql, new Object[] { name }, getRowMapper());

			return (ArrayList<College>) colleges;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("There is no College with that name!");
			return null;
		}
	}

	/**
	 * Row Mapper.
	 */
	@Override
	public RowMapper<College> getRowMapper() {
		return new RowMapper<College>() {
			public College mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				College college = new College();
				college.setId(rs.getLong("id"));
				college.setName(rs.getString("name"));
				college.setId(rs.getLong("college_id"));

				return college;
			}
		};
	}

	/**
	 * Creates the connection to the database.
	 */
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final College college) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into college (id, name) values (?,?) " + "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, college.getId());
				ps.setString(2, college.getName());
				return ps;
			}
		};
	}
}