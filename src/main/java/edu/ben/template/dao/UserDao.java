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

import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;

public class UserDao extends BaseDao<User> {

	private MajorDao majorDao;
	private InterestDao interestDao;

	public UserDao() {
		super();
		this.majorDao = new MajorDao();
		this.interestDao = new InterestDao();

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

		String sql = "UPDATE user SET bnumber = ?, email = ?, personal_email = ?, password = ?, salt = ?, first_name= ?, last_name = ?, role = ?, graduation_year = ?, occupation = ?, title = ?, suffix = ?, bio = ?, experience = ?, hidden = 1, active = 1, created = null, last_active = null, last_modified = null, social_media = null WHERE id = ?";

		// TODO Change this include last last six columns

		jdbcTemplate.update(sql,
				new Object[] { user.getbNumber(), user.getEmail(), user.getPersonalEmail(), user.getPassword(),
						user.getSalt(), user.getFirstName(), user.getLastName(), user.getRole(),
						user.getGraduationYear(), user.getOccupation(), user.getTitle(), user.getSuffix(),
						user.getBio(), user.getExperience(), user.getId(), });

	}
	
	public void addMultiple(User user, String file){
		
		
		String sql = "LOAD DATA INFILE '" +file+ "' INTO TABLE user FIELDS TERMINATED BY ','" + "LINES TERMINATED BY '\n' (bnumber, email, personal_email, password, salt, first_name, last_name, role, graduation_year, occupation, title, suffix, bio, experience, hidden, active, created, last_active, last_modified, social_media) WHERE id = ?;";
		
		jdbcTemplate.update(sql,
				new Object[] {  user.getbNumber(), user.getEmail(), user.getPersonalEmail(),
						user.getPassword(), user.getSalt(), user.getFirstName(), user.getLastName(), user.getRole(),
						user.getGraduationYear(), user.getOccupation(), user.getTitle(), user.getSuffix(),
						user.getBio(), user.getExperience(),user.getId(), });
	}

	public ArrayList<User> findAll() {

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
	public ArrayList<User> findAllStudents() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from alumnitracker.user WHERE role = '1'";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("There are not any students in the system.");
			return null;
		}
	}

	/**
	 * Method to find all the alumni in the database.
	 * 
	 * @return the amount of alumni registered.
	 */
	public ArrayList<User> findAllAlumni() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from alumnitracker.user WHERE role = '2'";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("There are not any alumni in the system.");
			return null;
		}
	}

	/**
	 * Method to find all the faculty in the database.
	 * 
	 * @return the amount of faculty registered.
	 */
	public ArrayList<User> findAllFaculty() {

		List<User> users = new ArrayList<User>();
		String sql = "SELECT * from alumnitracker.user WHERE role = '3'";

		try {
			users = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<User>) users;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("There are not any faculty in the system.");
			return null;
		}
	}

	public User findByEmail(String email) {

		User u = null;
		try {

			String sql = "SELECT user.id as id, user.bnumber, user.email, user.personal_email, user.password, user.salt, user.first_name, user.last_name, user.role, user.graduation_year,user.occupation, user.title, user.suffix, user.bio, user.experience FROM user WHERE user.email = ? GROUP BY id";
			u = jdbcTemplate.queryForObject(sql, new Object[] { email }, getRowMapper());
		} catch (Exception e) {
			return null;
		}
		return u;

	}

	public User findByPersonalEmail(String email) {

		User u = null;
		try {
			String sql = "SELECT user.id as id, user.bnumber, user.email, user.personal_email, user.password, user.salt, user.first_name, user.last_name, user.role, user.graduation_year,user.occupation, user.title, user.suffix, user.bio, user.experience FROM user WHERE user.personal_email = ? GROUP BY id";
			u = jdbcTemplate.queryForObject(sql, new Object[] { email }, getRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return u;

	}

	public void updateUser(User user) {

		String sql = "UPDATE user SET bnumber = ?, first_name = ?, last_name = ?, email = ?, personal_email = ?, password = ?, salt = ?, role = ?, graduation_year = ?, occupation = ?, title = ?, suffix = ?, experience = ?, bio = ?, role = ? WHERE user.id = ?";
		try {
			jdbcTemplate.update(sql,
					new Object[] { user.getbNumber(), user.getFirstName(), user.getLastName(), user.getEmail(),
							user.getPersonalEmail(), user.getPassword(), user.getSalt(), user.getRole(),
							user.getGraduationYear(), user.getOccupation(), user.getTitle(), user.getSuffix(),
							user.getExperience(), user.getBio(), user.getRole(), user.getId(), });

		} catch (Exception e) {
			/* Probably want to log this */
		}
		return;

	}

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
				user.setBio(rs.getString("bio"));
				user.setExperience(rs.getString("experience"));

				// TODO See Pollack about structure change (INEFFICIENT)

				// user.setConcentration(majorDao.findConcentrationByUser(user));
				// user.setMinor(majorDao.findMinorByUser(user));
				// user.setMajor(majorDao.findMajorByUser(user));
				// user.setInterest(interestDao.findAllByUser(user));

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
