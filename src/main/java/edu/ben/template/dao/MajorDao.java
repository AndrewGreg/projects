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

import edu.ben.template.model.Major;
import edu.ben.template.model.User;

public class MajorDao extends BaseDao<Major> {

	// add a user's major
	// add a user's minor
	// add a user's concentration
	
	public MajorDao(){
		super();
	}

	public Major getObjectById(int objectId) {
		return this.getObjectById(objectId, false);
	}

	public Major getObjectById(int objectId, boolean complete) {
		if (objectId == 0) {
			/* Probably want to log this */
			return null;
		}
		Major object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM major WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { objectId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public ArrayList<Major> findAll() {

		List<Major> majors = new ArrayList<Major>();
		String sql = "SELECT * from major";

		try {
			majors = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Major>) majors;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Major> findAllMajors() {

		List<Major> majors = new ArrayList<Major>();
		String sql = "SELECT * from major WHERE concentration = 0";

		try {
			majors = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Major>) majors;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Major> findAllConcentrations() {

		List<Major> concentrations = new ArrayList<Major>();
		String sql = "SELECT * from major WHERE concentration = 1";

		try {
			concentrations = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Major>) concentrations;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	// Search for concentrations of a major
	public ArrayList<Major> findConcentrationByMajor(Major m) {

		List<Major> majors = new ArrayList<Major>();
		String sql = "SELECT c.id, c.name, c.major_id FROM major c, major m WHERE c.concentration = 1 AND c.major_id = m.id AND m.id = ?;";

		try {
			majors = jdbcTemplate.query(sql, new Object[] { m.getId() }, getRowMapper());
			return (ArrayList<Major>) majors;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Major> findMajorByUser(User u) {

		List<Major> majors = new ArrayList<Major>();
		String sql = "SELECT m.id, m.name FROM user_major um JOIN user u on u.id = um.user_id JOIN major m ON m.id = um.major_id WHERE u.id = ? AND m.concentration = 0 AND um.minor = 0;";

		try {
			majors = jdbcTemplate.query(sql, new Object[] { u.getId() }, getRowMapper());
			return (ArrayList<Major>) majors;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Major> findMinorByUser(User u) {

		List<Major> minors = new ArrayList<Major>();
		String sql = "SELECT m.id, m.name FROM user_major um JOIN user u on u.id = um.user_id JOIN major m ON m.id = um.major_id WHERE u.id = ? AND m.concentration = 0 AND um.minor = 1;";

		try {
			minors = jdbcTemplate.query(sql, new Object[] { u.getId() }, getRowMapper());
			return (ArrayList<Major>) minors;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Major> findConcentrationByUser(User u) {

		List<Major> concentrations = new ArrayList<Major>();
		
		String sql = "SELECT m.id, m.name, m.concentration FROM user_major um JOIN user u ON u.id = um.user_id JOIN major m ON m.id = um.major_id WHERE u.id = ? AND m.concentration = 1;";
		System.out.println(u.getId());
		
		try {
			
			System.out.println(u.getId());
			concentrations = jdbcTemplate.query(sql, new Object[] { u.getId() }, getRowMapper());
			
			return (ArrayList<Major>) concentrations;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	// Find Parent Majors of Concentrations
	public ArrayList<Major> findMajorByConcentration(Major m) {

		if (m.isConcentration()) {

			List<Major> majors = new ArrayList<Major>();
			String sql = "SELECT m.id, m.name, m.concentration FROM major c, major m WHERE c.concentration = 1 AND c.major_id = m.id AND c.id = ?;";

			try {
				majors = jdbcTemplate.query(sql, new Object[] { m.getId() }, getRowMapper());
				return (ArrayList<Major>) majors;
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return null;
	}

	public void addMajor(Major major) {

		String sql = "INSERT INTO major (name, concentration) VALUES (?,?)";

		jdbcTemplate.update(sql, major.getName(), major.isConcentration());
		return;
	}
	

	public void addConcentration(Major major) {

		String sql = "INSERT INTO major (name, concentration) VALUES (?,?)";

		jdbcTemplate.update(sql, major.getName(), major.isConcentration());
		return;
	}
	
	public void addMajorToUser(Major major, User u) {

		String sql = "INSERT INTO user_major (user_id,major_id,minor) VALUES (?,?,0);";

		jdbcTemplate.update(sql, u.getId(), major.getId());
		return;
	}
	
	public void addMinorToUser(Major major, User u) {

		String sql = "INSERT INTO user_major (user_id,major_id,minor) VALUES (?,?,1);";

		jdbcTemplate.update(sql, major.getName(), major.isConcentration());
		return;
	}
	
	public void addConcentrationToUser(Major major, User u) {

		String sql = "INSERT INTO user_major (user_id,major_id,minor) VALUES (?,?,0);";

		jdbcTemplate.update(sql, major.getName(), major.isConcentration());
		return;
	}

	@Override
	public RowMapper<Major> getRowMapper() {
		return new RowMapper<Major>() {
			public Major mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Major major = new Major();
				major.setId(rs.getLong("id"));
				major.setName(rs.getString("name"));
				major.setConcentration(rs.getBoolean("concentration"));

				// return the object
				return major;
			}
		};
	}

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Major major) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into major (id, name, concentration) values (?,?,?) "
								+ "on duplicate key update name = values(name), concentration = values(concentration)",
						new String[] { "id" });
				ps.setLong(1, major.getId());
				ps.setString(2, major.getName());
				ps.setBoolean(3, major.isConcentration());
				return ps;
			}
		};
	}

}
