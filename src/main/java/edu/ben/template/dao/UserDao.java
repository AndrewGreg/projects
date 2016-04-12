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

	static final String SEARCH = "SELECT * FROM ";

	public UserDao() {
		super();
	}

	public User getObjectById(long userId) {
		return this.getObjectById(userId, false);
	}

	public User getObjectById(long userId, boolean complete) {
		if (userId == 0) {
			/* Probably want to log this */
			return null;
		}
		User object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM user WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { userId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public void addUser(User user) {

		// Created, lastModified, lastLogin, lastActive will be implemented in
		// future sprint
		String sql = "INSERT INTO user (`bnumber`, `email`, `personal_email`, `password`, `salt`, `title_id`, `first_name`, `last_name`, `role`, `graduation_year`, `occupation`, `company`, `suffix`, `biography`, `experience`, `hidden`, `active`, `social_media`, `phone_number`, `work_number`, `user_verified`, `admin_verified`, `graduate_verified`, `current_graduate_verified`, `graduate_school`, `public`, `reference`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, null, null, null, null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			jdbcTemplate.update(sql,
					new Object[] { user.getbNumber(), user.getEmail(), user.getPersonalEmail(), user.getPassword(),
							user.getSalt(), user.getTitleID(), user.getFirstName(), user.getLastName(), user.getRole(),
							user.getGraduationYear(), user.getOccupation(), user.getCompany(), user.getSuffix(),
							user.getBiography(), user.getExperience(), user.isHidden(), user.isActive(),
							user.getSocialMedia(), user.getPhoneNumber(), user.getWorkNumber(), user.isUserVerified(),
							user.isAdminVerified(), user.isGraduateVerified(), user.isCurrentGraduateVerified(),
							user.getGraduateSchool(), user.getToPublic(), user.getReference() });
		} catch (Exception e) {
			/* Probably want to log this */
		}

	}

	// TODO Not Tested in Sprint 3
	public void addMultiple(String file) {

		User user = new User();
		String sql = "LOAD DATA INFILE '" + file + "' INTO TABLE user FIELDS TERMINATED BY ','"
				+ "LINES TERMINATED BY '\n' (bnumber, email, personal_email, password, salt, first_name, last_name, role, graduation_year, occupation, titleID, suffix, biography, experience, hidden, active, created, last_active, last_modified, social_media) WHERE id = ?;";

		jdbcTemplate.update(sql,
				new Object[] { user.getbNumber(), user.getEmail(), user.getPersonalEmail(), user.getPassword(),
						user.getSalt(), user.getFirstName(), user.getLastName(), user.getRole(),
						user.getGraduationYear(), user.getOccupation(), user.getTitleID(), user.getSuffix(),
						user.getBiography(), user.getExperience(), user.getId(), });
	}

	public ArrayList<User> getAll() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from user";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Method to find all the students in the database.
	 * 
	 * @return the amount of students registered.
	 */
	public ArrayList<User> getAllStudents() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from user WHERE role = '1'";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int getStudentCount() {

		int count = 0;
		String sql = "SELECT COUNT(*) FROM user WHERE role = '1';";

		try {

			count = jdbcTemplate.queryForInt(sql, getRowMapper());
			return count;

		} catch (EmptyResultDataAccessException e) {
			
			return count;
		}
	}

	/**
	 * Method to find all the alumni in the database.
	 * 
	 * @return the amount of alumni registered.
	 */
	public ArrayList<User> getAllAlumni() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from user WHERE role = '2'";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			
			return null;
		}
	}

	public int getAlumniCount() {

		int count = 0;
		String sql = "SELECT COUNT(*) FROM user WHERE role = '2';";

		try {

			count = jdbcTemplate.queryForInt(sql, getRowMapper());
			return count;

		} catch (EmptyResultDataAccessException e) {
			
			return count;
		}
	}

	/**
	 * Method to find all the faculty in the database.
	 * 
	 * @return the amount of faculty registered.
	 */
	public ArrayList<User> getAllFaculty() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from user WHERE role = '3'";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			
			return null;
		}
	}

	public int getFacultyCount() {

		int count = 0;
		String sql = "SELECT COUNT(*) FROM user WHERE role = '3';";

		try {

			count = jdbcTemplate.queryForInt(sql, getRowMapper());
			return count;

		} catch (EmptyResultDataAccessException e) {
		
			return count;
		}
	}

	public User getByEmail(String email) {

		User u = null;
		try {

			String sql = "SELECT * FROM user WHERE email = ? LIMIT 1";
			u = jdbcTemplate.queryForObject(sql, new Object[] { email }, getRowMapper());
		} catch (Exception e) {
			return null;
		}
		return u;

	}

	/**
	 * Sort by the year that user Graduated.
	 * 
	 * @return the graduation year of the user in ascending order.
	 */
	public ArrayList<User> sortByGradYear() {

		List<User> alumni = new ArrayList<User>();
		String sql = SEARCH + "user where graduation_year IS NOT NULL ORDER BY graduation_year ASC";

		try {
			alumni = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) alumni;
		} catch (EmptyResultDataAccessException e) {

			return null;
		}
	}

	/**
	 * Sort by the first name that user.
	 * 
	 * @return the first name of the user in ascending order.
	 */
	public ArrayList<User> sortByFirstName() {

		List<User> alumni = new ArrayList<User>();
		String sql = SEARCH + "user where first_name IS NOT NULL ORDER BY first_name ASC";

		try {
			alumni = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) alumni;
		} catch (EmptyResultDataAccessException e) {

			return null;
		}
	}

	/**
	 * Sort by the last name that user.
	 * 
	 * @return the last name of the user in ascending order.
	 */
	public ArrayList<User> sortByLastName() {

		List<User> alumni = new ArrayList<User>();
		String sql = SEARCH + "user where last_name IS NOT NULL ORDER BY last_name ASC";

		try {
			alumni = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) alumni;
		} catch (EmptyResultDataAccessException e) {

			return null;
		}
	}

	/**
	 * Sort by the major that user.
	 * 
	 * @return the major of the user in ascending order.
	 */
	public ArrayList<User> sortByMajor() {

		List<User> alumni = new ArrayList<User>();
		String sql = SEARCH
				+ "major JOIN user on major.name = concentration where name IS NOT NULL ORDER BY major.name ASC";

		try {
			alumni = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) alumni;
		} catch (EmptyResultDataAccessException e) {

			return null;
		}
	}

	public User getByPersonalEmail(String email) {

		User u = null;
		try {
			String sql = "SELECT * FROM user WHERE personal_email = ? LIMIT 1";
			u = jdbcTemplate.queryForObject(sql, new Object[] { email }, getRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return u;

	}

	// TODO GET ACCOUNT

	public void updateUser(User user) {


		String sql = "UPDATE user SET `bnumber`=?, `email`=?, `personal_email`=?, `password`=?, `salt`=?, `title_id`=?, `first_name`=?, `last_name`=?, `role`=?, `graduation_year`=?, `occupation`=?, `company`=?, `suffix`=?, `biography`=?, `experience`=?, `hidden`=?, `active`=?, `social_media`=?, `phone_number`=?, `work_number`=?, `user_verified`=?, `admin_verified`=?, `graduate_verified`=?, `current_graduate_verified`=?, `graduate_school`=?, `public`=?, `reference`=? WHERE `id`=?;";
		

		try {
			jdbcTemplate.update(sql,
					new Object[] { user.getbNumber(), user.getEmail(), user.getPersonalEmail(), user.getPassword(),
							user.getSalt(), user.getTitleID(), user.getFirstName(), user.getLastName(), user.getRole(),
							user.getGraduationYear(), user.getOccupation(), user.getCompany(), user.getSuffix(),
							user.getBiography(), user.getExperience(), user.isHidden(), user.isActive(),
							user.getSocialMedia(), user.getPhoneNumber(), user.getWorkNumber(), user.isUserVerified(),
							user.isAdminVerified(), user.isGraduateVerified(), user.isCurrentGraduateVerified(),
							user.getGraduateSchool(), user.getToPublic(), user.getReference(), user.getId() });
		} catch (Exception e) {
			/* Probably want to log this */
			e.printStackTrace();
		}
		return;

	}

	@Override
	public RowMapper<User> getRowMapper() {
		return new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				User user = new User();
				user.setEmail(rs.getString("email"));
				user.setPersonalEmail(rs.getString("personal_email"));
				user.setId(rs.getLong("id"));
				user.setbNumber(rs.getInt("bnumber"));
				user.setPassword(rs.getString("password"));
				user.setSalt(rs.getString("salt"));
				user.setTitleID(rs.getInt("title_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setRole(rs.getInt("role"));
				user.setGraduationYear(rs.getInt("graduation_year"));
				user.setOccupation(rs.getString("occupation"));
				user.setCompany(rs.getString("company"));
				user.setSuffix(rs.getString("suffix"));
				user.setBiography(rs.getString("biography"));
				user.setExperience(rs.getString("experience"));
				user.setHidden(rs.getBoolean("hidden"));
				user.setActive(rs.getBoolean("active"));
				user.setSocialMedia(rs.getString("social_media"));
				user.setPhoneNumber(rs.getString("phone_number"));
				user.setWorkNumber(rs.getString("work_number"));
				user.setUserVerified(rs.getBoolean("user_verified"));
				user.setAdminVerified(rs.getBoolean("admin_verified"));
				user.setGraduateVerified(rs.getBoolean("graduate_verified"));
				user.setCurrentGraduateVerified(rs.getBoolean("current_graduate_verified"));
				user.setGraduateSchool(rs.getString("graduate_school"));
				user.setToPublic(rs.getInt("public"));
				user.setReference(rs.getString("reference"));

				// user.setCreated(rs.getDateTime("created"));
				// user.setLastActive(rs.getDateTime("lastActive"));
				// user.setLastModified(rs.getDateTime("lastModified"));
				// user.setLastLogin(rs.getDateTime("lastLogin"));

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
