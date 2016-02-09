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

import edu.ben.template.model.User;

public class UserDao extends BaseDao<User> {

	public UserDao() {
	}

	public User getObjectById(int objectId) {
		return this.getObjectById(objectId, false);
	}

	public User getObjectById(int objectId, boolean complete) {
		if (objectId == 0) {
			/*  Probably want to log this */
			return null;
		}
		User object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM user WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { objectId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/*  Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public void addUser(User user) {

		String sql = "INSERT INTO user (username, password, salt, first_name, last_name, email, personal_email, role, title, suffix, graduation_year, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getFirstName(),
				user.getLastName(), user.getEmail(), user.getPersonalEmail(), user.getRole(), user.getTitle(),
				user.getSuffix(), user.getGraduationYear(), user.getOccupation());
		return;
	}

	public  ArrayList<User> findAll() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from user";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			/*  Probably want to log this */
			return null;
		}
	}

	public User findByEmail(String email) {

		User u = null;
		try {
			String sql = "SELECT user.id as id, user.bnumber, user.email, user.personal_email, user.password, user.salt, user.first_name, user.last_name, user.role, user.graduation_year, user.title, user.suffix FROM user WHERE user.email = ? GROUP BY id";
			u = jdbcTemplate.queryForObject(sql, new Object[] { email }, getRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return u;

	}

	public User findByPersonalEmail(String email) {

		User u = null;
		try {
			String sql = "SELECT user.id as id, user.bnumber, user.email, user.personal_email, user.password, user.salt, user.first_name, user.last_name, user.role, user.graduation_year, user.title, user.suffix FROM user WHERE user.personal_email = ? GROUP BY id";
			u = jdbcTemplate.queryForObject(sql, new Object[] { email }, getRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return u;

	}

	public void updateUser(User user) {
		
		String sql = "UPDATE user SET bnumber = ?, first_name = ?, last_name = ?, email = ?, personal_email = ?, password = ?, salt = ?, role = ?, graduation_year = ?, occupation = ?, title = ?, suffix = ? WHERE user.id = ?";
		try {
			jdbcTemplate.update(sql, user.getbNumber(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPersonalEmail(),
					user.getPassword(), user.getSalt(), user.getRole(), user.getGraduationYear(), user.getOccupation(), user.getTitle(), user.getSuffix(), user.getId());
		} catch (Exception e) {
			/*  Probably want to log this */
		}
		return;
		
	}

	// Prof. Pollack's template code
	// /**
	// * FOR LOGIN
	// *
	// * @param email
	// * @return User from database that matches the email provided
	// * @throws SQLException
	// */
	// public User getUserByEmail(String email) {
	// return getUserByEmail(email, true);
	// }

	// public User getUserByEmail(String email, boolean active) {
	// User user = null;
	//
	// try {
	// // look up the object
	// String sql = "select * from user where email = ? and user.is_active = ?";
	// user = this.jdbcTemplate.queryForObject(sql, new Object[] { email, active
	// }, getRowMapper());
	// } catch (EmptyResultDataAccessException e) {
	// return null;
	// }
	// return user;
	// }

	@Override
	public RowMapper<User> getRowMapper() {
		return new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPersonalEmail(rs.getString("personal_email"));
				user.setPassword(rs.getString("password"));
				user.setGraduationYear(rs.getInt("graduation_year"));
				user.setSalt(rs.getString("salt"));
				user.setOccupation(rs.getString("occupation"));
				user.setTitle(rs.getString("title"));
				user.setSuffix(rs.getString("suffix"));
				user.setbNumber(rs.getInt("bnumber"));
				user.setRole(rs.getInt("role"));
				// TODO add ArrayList<String> Major(s), ArrayList<String>
				// Minor(s), ArrayList<String> Concentration(s) using dao calls
				// to tables "major" and "user_major"

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
						"insert into user (id, email, password) values (?,?,?) "
								+ "on duplicate key update email = values(email), password = values(password)",
						new String[] { "id" });
				ps.setLong(1, user.getId());
				ps.setString(2, user.getEmail());
				return ps;
			}
		};
	}
}
