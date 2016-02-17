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

import edu.ben.template.model.Event;
import edu.ben.template.model.Interest;
import edu.ben.template.model.Major;
import edu.ben.template.model.User;
import edu.ben.template.model.JobPosting;

public class InterestDao extends BaseDao<Interest> {

	// Add interests from ArrayList<Interest>?

	public Interest getObjectById(int objectId) {
		return this.getObjectById(objectId, false);
	}

	public Interest getObjectById(int objectId, boolean complete) {
		if (objectId == 0) {
			/* Probably want to log this */
			return null;
		}
		Interest object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM interest WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { objectId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public ArrayList<Interest> findAll() {

		List<Interest> interests = new ArrayList<Interest>();
		String sql = "SELECT * from interest";

		try {
			interests = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Interest>) interests;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Interest> findAllByUser(User u) {

		List<Interest> interests = new ArrayList<Interest>();
		String sql = "SELECT i.id, i.name FROM user_interest ui JOIN user u ON u.id = ui.user_id JOIN interest i ON i.id = ui.interest_id  WHERE u.id = ?;";

		try {
			interests = jdbcTemplate.query(sql, new Object[] { u.getId() }, getRowMapper());
			return (ArrayList<Interest>) interests;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Interest> findAllByJob(JobPosting j) {

		List<Interest> interests = new ArrayList<Interest>();
		String sql = "SELECT i.name FROM job_interest ji JOIN job j ON j.id = ji.job_id JOIN interest i ON i.id = ji.interest_id WHERE j.id = ?;";

		try {
			interests = jdbcTemplate.query(sql, new Object[] { j.getId() }, getRowMapper());
			return (ArrayList<Interest>) interests;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Interest> findAllByEvent(Event e) {

		List<Interest> interests = new ArrayList<Interest>();
		String sql = "SELECT i.name FROM event_interest ei JOIN event e ON e.id = ei.event_id JOIN interest i ON i.id = ei.interest_id WHERE e.id = ?;";

		try {
			interests = jdbcTemplate.query(sql, new Object[] { e.getId() }, getRowMapper());
			return (ArrayList<Interest>) interests;
		} catch (EmptyResultDataAccessException eR) {
			/* Probably want to log this */
			return null;
		}
	}

	public void addInterest(Interest interest) {

		String sql = "INSERT INTO interest (name) VALUES (?)";

		jdbcTemplate.update(sql, interest.getName());
		return;
	}

	public void addInterestToUser(User u, Interest i) {

		String sql = "INSERT INTO user_interest (user_id, interest_id) values (?,?);";

		jdbcTemplate.update(sql, new Object[] { u.getId(), i.getId() });
		return;
	}

	public void addInterestToEvent(User u, Event e) {

		String sql = "INSERT INTO event_interest (event_id, interest_id) VALUE (?,?);";

		jdbcTemplate.update(sql, new Object[] { u.getId(), e.getId() });
		return;
	}

	public void addInterestToJobPosting(User u, JobPosting j) {

		String sql = "INSERT INTO job_interest (job_id,interest_id) VALUE (?,?);";

		jdbcTemplate.update(sql, new Object[] { u.getId(), j.getId() });
		return;
	}

	@Override
	public RowMapper<Interest> getRowMapper() {
		return new RowMapper<Interest>() {
			public Interest mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Interest interest = new Interest();
				interest.setId(rs.getLong("id"));
				interest.setName(rs.getString("name"));

				// return the object
				return interest;
			}
		};
	}

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Interest interest) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into interest (id, name) values (?,?) " + "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, interest.getId());
				ps.setString(2, interest.getName());
				return ps;
			}
		};
	}

}
