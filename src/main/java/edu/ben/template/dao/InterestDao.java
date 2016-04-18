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
import edu.ben.template.model.User;
import edu.ben.template.model.Job;

public class InterestDao extends BaseDao<Interest> {

	public InterestDao() {
		super();
	}

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

	public ArrayList<Interest> getAll() {

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

	public Interest getByName(String name) {

		String sql = "SELECT * FROM interest WHERE name = ?;";

		try {
			Interest i = jdbcTemplate.queryForObject(sql, new Object[] { name }, getRowMapper());
			return i;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Interest> getAllByUser(User u) {

		List<Interest> interests = new ArrayList<Interest>();
		String sql = "SELECT i.id, i.name, i.hidden FROM user_interest ui JOIN user u ON u.id = ui.user_id JOIN interest i ON i.id = ui.interest_id  WHERE u.id = ?;";

		try {
			interests = jdbcTemplate.query(sql, new Object[] { u.getId() }, getRowMapper());
			return (ArrayList<Interest>) interests;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Interest> getAllByJobPosting(Job j) {

		List<Interest> interests = new ArrayList<Interest>();
		String sql = "SELECT i.id, i.name, i.hidden FROM job_interest ji JOIN job j ON j.id = ji.job_id JOIN interest i ON i.id = ji.interest_id WHERE j.id = ?;";

		try {
			interests = jdbcTemplate.query(sql, new Object[] { j.getId() }, getRowMapper());
			return (ArrayList<Interest>) interests;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Interest> getAllByEvent(Event e) {

		List<Interest> interests = new ArrayList<Interest>();
		String sql = "SELECT i.id, i.name, i.hidden FROM event_interest ei JOIN event e ON e.id = ei.event_id JOIN interest i ON i.id = ei.interest_id WHERE e.id = ?;";

		try {
			interests = jdbcTemplate.query(sql, new Object[] { e.getId() }, getRowMapper());
			return (ArrayList<Interest>) interests;
		} catch (EmptyResultDataAccessException eR) {
			/* Probably want to log this */
			return null;
		}
	}

	public void addInterest(Interest interest) {

		String sql = "INSERT INTO interest (name,hidden) VALUES (?,0)";

		try {
			jdbcTemplate.update(sql, new Object[] { interest.getName() });
		} catch (Exception e) {
			/* Probably want to log this */
		}
	}

	public void addInterestToUser(User u, Interest i) {

		String sql = "INSERT INTO user_interest (user_id, interest_id) VALUES (?,?);";

		try {
			jdbcTemplate.update(sql, u.getId(), i.getId());
			return;
		} catch (Exception e) {
			/* Probably should log this */
		}
	}

	public void addInterestToEvent(Event event, Interest interest) {

		String sql = "INSERT INTO event_interest (event_id, interest_id) VALUES (?,?);";

		try {
			jdbcTemplate.update(sql, event.getId(), interest.getId());
			return;
		} catch (Exception ex) {
			/* Probably should log this */
		}
	}

	public void addInterestToJobPosting(Job job, Interest interest) {

		String sql = "INSERT INTO job_interest (job_id,interest_id) VALUES (?,?);";

		try {
			jdbcTemplate.update(sql, job.getId(), interest.getId());
			return;
		} catch (Exception e) {
			/* Probably should log this */
		}
	}

	public void clearUserInterest(User user) {

		String sql = "DELETE FROM user_interest where user_id = ?;";

		try {
			jdbcTemplate.update(sql, user.getId());
			return;
		} catch (Exception e) {
			/* Probably should log this */
		}
	}

	public void updateInterest(Interest interest) {

		String sql = "UPDATE interest SET `name`=?, `hidden`=? WHERE `id`=?;";

		try {
			jdbcTemplate.update(sql, interest.getName(), interest.isHidden(), interest.getId());
			return;
		} catch (Exception e) {
			/* Probably should log this */
		}
	}

	@Override
	public RowMapper<Interest> getRowMapper() {
		return new RowMapper<Interest>() {
			@Override
			public Interest mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Interest interest = new Interest();
				interest.setId(rs.getLong("id"));
				interest.setName(rs.getString("name"));
				interest.setHidden(rs.getBoolean("hidden"));

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
