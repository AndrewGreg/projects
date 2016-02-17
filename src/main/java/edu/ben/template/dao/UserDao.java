package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import edu.ben.template.model.User;

public class UserDao extends BaseDao<User> {

	public UserDao() {
	}

	public User getObjectById(long objectId) {
		return this.getObjectById(objectId, false);
	}

	public User getObjectById(long objectId, boolean complete) {
		if (objectId == 0) {
			/* TODO Probably want to log this */
			return null;
		}
		User object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM user WHERE user_id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { objectId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* TODO Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public User getUserByEmail(String email, boolean active) {
		User user = null;

		try {
			// look up the object
			String sql = "select * from user where email = ? and user.is_active = ?";
			user = this.jdbcTemplate.queryForObject(sql, new Object[] { email, active }, getRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	/**
	 * FOR LOGIN
	 * 
	 * @param email
	 * @return User from database that matches the email provided
	 * @throws SQLException
	 */
	public User getUserByEmail(String email) {
		return getUserByEmail(email, true);
	}

	@Override
	public RowMapper<User> getRowMapper() {
		return new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				User user = new User();
				user.setId(rs.getLong("user_id"));
				user.setFirstName(rs.getString("first_name"));
				// return the object
				return user;
			}
		};
	}

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final User user) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into user (user_id, email, password) values (?,?,?) "
								+ "on duplicate key update email = values(email), password = values(password)",
						new String[] { "user_id" });
				ps.setLong(1, user.getId());
				ps.setString(2, user.getEmail());
				return ps;
			}
		};
	}
}
