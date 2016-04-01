package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import edu.ben.template.model.Interest;
import edu.ben.template.model.Job;
import edu.ben.template.model.User;

/**
 * This Dao controls the object class of Job
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class JobDao extends BaseDao<Job> {

	static final String SEARCH = "SELECT * FROM ";

	// Passes in the User signed in.
	@Autowired
	private UserDao userDao;

	/**
	 * Super constructor
	 */
	public JobDao() {
		super();
	}

	/**
	 * Grab the job object by Id. We use this one.
	 * 
	 * @param jobId
	 *            is type long
	 * @return the object by id.
	 */
	public Job getObjectById(long jobId) {
		return this.getObjectById(jobId, false);
	}

	/**
	 * Grabs the job object by it's Id depending on the boolean type. We don't
	 * really use this one.
	 * 
	 * @param jobId
	 * @param complete
	 * @return the Id of the object.
	 */
	public Job getObjectById(long jobId, boolean complete) {
		if (jobId == 0) {
			/* Probably want to log this */
			return null;
		}
		Job object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = SEARCH + "job WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { jobId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	/**
	 * Retrieves all Jobs from database.
	 * 
	 * @return all jobs.
	 */
	public ArrayList<Job> getAll() {

		List<Job> jobs = new ArrayList<Job>();
		String sql = SEARCH + "job ORDER BY name";

		try {
			jobs = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Job>) jobs;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Inserts the Job into the database.
	 * 
	 * @param job
	 *            entails of name, description, company, the poster, and more.
	 */
	public void addJob(Job job) {

		String sql = "INSERT INTO job (name, description, company,location,user_id, reference, public, hours_id, salary,start_wage, end_wage,start_salary,end_salary, link) VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)";

		jdbcTemplate.update(sql,
				new Object[] { job.getName(), job.getDescription(), job.getCompany(), job.getLocation(),
						job.getPoster().getId(), job.getReference(), job.isToPublic(), job.getHours(), job.getSalary(),
						job.getStart_wage(), job.getEnd_wage(), job.getStart_salary(), job.getEnd_salary(),
						job.getLink() });

		return;
	}

	/**
	 * Enables the user to edit the following attributes.
	 * 
	 * @param job
	 */
	public void updateJob(Job job) {

		String sql = "UPDATE job SET name = ?, description = ?, company = ?, start_wage = ?,end_wage = ?, start_salary = ?, end_salary = ?, location = ?, salary = ?, reference = ?, public = ?, hours_id = ?, link = ?, user_id = ? WHERE job.id = ?";
		try {
			jdbcTemplate.update(sql,
					new Object[] { job.getName(), job.getDescription(), job.getCompany(), job.getStart_wage(),
							job.getEnd_wage(), job.getStart_salary(), job.getEnd_salary(), job.getLocation(),
							job.getSalary(), job.getReference(), job.isToPublic(), job.getHours(), job.getLink(),
							job.getPoster().getId(), job.getId() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;

	}

	/**
	 * Searches by name of the job in ascending order.
	 * 
	 * @param job
	 * 
	 * @return the name of the job.
	 */
	public ArrayList<Job> getSearchByJobName(String name) {

		List<Job> jobs = new ArrayList<Job>();
		String sql = "SELECT name FROM job WHERE name LIKE '%name%' ORDER BY name";

		try {
			// Test this.
			jobs = jdbcTemplate.query(sql, new Object[] { name }, getRowMapper());

			return (ArrayList<Job>) jobs;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("There are no jobs with that search!");
			return null;
		}
	}

	/**
	 * Retrieve the user who created the job.
	 * 
	 * @param user
	 *            of job.
	 * @return the Poster of the job.
	 */
	public ArrayList<Job> getByPoster(User user) {

		List<Job> jobs = new ArrayList<Job>();
		String sql = SEARCH + "job WHERE user_id = ?";

		try {
			jobs = jdbcTemplate.query(sql, new Object[] { user.getId() }, getRowMapper());// TEST
																							// THIS
			return (ArrayList<Job>) jobs;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Retrieve the top 5 Highest Paid jobs.
	 * 
	 * @param user
	 *            of job.
	 * @return the 5 of the highest paid jobs by wage.
	 */
	public ArrayList<Job> getByHighestPaidWage() {

		List<Job> jobs = new ArrayList<Job>();
		String sql = SEARCH
				+ "job WHERE job.start_wage is NOT NULL AND job.end_wage is NOT NULL order by job.start_wage desc Limit 6";

		try {
			jobs = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<Job>) jobs;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("There are no jobs.");
			return null;
		}
	}

	/**
	 * Retrieve the top 5 Highest Paid jobs.
	 * 
	 * @param user
	 *            of job.
	 * @return the 5 of the highest paid jobs by salary.
	 */
	public ArrayList<Job> getByHighestPaidSalary() {

		List<Job> jobs = new ArrayList<Job>();
		String sql = SEARCH
				+ "job WHERE job.start_salary is NOT NULL AND job.end_salary is NOT NULL order by job.start_salary desc Limit 6";

		try {
			jobs = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<Job>) jobs;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("There are no jobs.");
			return null;
		}
	}

	/**
	 * Retrieve the area of interest that are associated with that job.
	 * 
	 * @param interest
	 * @return events by areas of interest they are associated with.
	 */
	public ArrayList<Job> getByInterest(Interest interest) {

		// Returns duplicate rows?
		String sql = "SELECT j.id, j.name, j.company, j.description FROM  job_interest ji JOIN job j on ji.job_id = j.id JOIN interest i on ji.interest_id = i.id WHERE i.id = ?";
		List<Job> events = new ArrayList<Job>();

		try {
			events = jdbcTemplate.query(sql, new Object[] { interest.getId() }, getRowMapper());// TEST
																								// THIS
			return (ArrayList<Job>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Row Mapper.
	 */
	@Override
	public RowMapper<Job> getRowMapper() {
		return new RowMapper<Job>() {
			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Job job = new Job();
				job.setId(rs.getLong("id"));
				job.setName(rs.getString("name"));
				job.setDescription(rs.getString("description"));
				job.setCompany(rs.getString("company"));
				job.setLocation(rs.getString("location"));
				job.setSalary(rs.getBoolean("salary"));
				job.setStart_wage(rs.getFloat("start_wage"));
				job.setEnd_wage(rs.getFloat("start_wage"));
				job.setStart_salary(rs.getInt("start_salary"));
				job.setEnd_salary(rs.getInt("end_salary"));
				job.setHours(rs.getInt("hours_id"));
				job.setLink(rs.getString("link"));
				job.setReference(rs.getString("reference"));
				job.setToPublic(rs.getInt("public"));

				// Grabs the id of the user that created the job.
				// Displays who posted the job.
				long userId = rs.getLong("user_id");
				User poster = userDao.getObjectById(userId);
				job.setPoster(poster);

				return job;
			}
		};
	}

	/**
	 * Creates the connection to the database.
	 */
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Job job) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into job (id, name) values (?,?) " + "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, job.getId());
				ps.setString(2, job.getName());
				return ps;
			}
		};
	}

}
