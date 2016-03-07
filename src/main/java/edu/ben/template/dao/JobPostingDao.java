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

import edu.ben.template.model.Event;
import edu.ben.template.model.Interest;
import edu.ben.template.model.JobPosting;
import edu.ben.template.model.User;

/**
 * This Dao controls the object class of Jobs
 * 
 * @author donald
 *
 */
public class JobPostingDao extends BaseDao<JobPosting> {

	@Autowired
	private UserDao userDao;

	/**
	 * Super constructor
	 */
	public JobPostingDao() {
		super();
	}

	/**
	 * Grab the job object by Id.
	 * 
	 * @param jobId
	 *            is type long
	 * @return the object by id.
	 */
	public JobPosting getObjectById(long jobId) {
		return this.getObjectById(jobId, false);
	}

	/**
	 * Grabs the job object by it's Id depending on the boolean type.
	 * 
	 * @param jobId
	 * @param complete
	 * @return the Id of the object.
	 */
	public JobPosting getObjectById(long jobId, boolean complete) {
		if (jobId == 0) {
			/* Probably want to log this */
			return null;
		}
		JobPosting object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM job WHERE id = ?";
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
	public ArrayList<JobPosting> getAll() {

		List<JobPosting> events = new ArrayList<JobPosting>();
		String sql = "SELECT * from job";

		try {
			events = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<JobPosting>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
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
	public ArrayList<JobPosting> getByPoster(User user) {

		List<JobPosting> jobs = new ArrayList<JobPosting>();
		String sql = "SELECT * from job WHERE user_id = ?";

		try {
			jobs = jdbcTemplate.query(sql, new Object[] { user.getId() }, getRowMapper());// TEST
																							// THIS
			return (ArrayList<JobPosting>) jobs;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Retrieve the area of interest that are associated with that job.
	 * 
	 * @param interest
	 * @return events by areas of interest they are associated with.
	 */
	public ArrayList<JobPosting> getByInterest(Interest interest) {

		// Returns duplicate rows?
		String sql = "SELECT j.id, j.name, j.company, j.description FROM  job_interest ji JOIN job j on ji.job_id = j.id JOIN interest i on ji.interest_id = i.id WHERE i.id = ?";
		List<JobPosting> events = new ArrayList<JobPosting>();

		try {
			events = jdbcTemplate.query(sql, new Object[] { interest.getId() }, getRowMapper());// TEST
			// THIS
			return (ArrayList<JobPosting>) events;
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
	public void addJobPosting(JobPosting job) {

		String sql = "INSERT INTO job (name, description, company, user_id) VALUES (?, ?, ?, ?)";

		jdbcTemplate.update(sql,
				new Object[] { job.getName(), job.getDescription(), job.getCompany(), job.getPoster().getId() });

		return;
	}

	/**
	 * Enables the user to edit the following attributes.
	 * 
	 * @param job
	 */
	public void updateJobPosting(JobPosting job) {

		String sql = "UPDATE job SET name = ?, description = ?, company = ?, user_id = ? WHERE job.id = ?";
		try {
			jdbcTemplate.update(sql, job.getName(), job.getDescription(), job.getCompany(), job.getPoster().getId(),
					job.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;

	}

	@Override
	public RowMapper<JobPosting> getRowMapper() {
		return new RowMapper<JobPosting>() {
			public JobPosting mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				JobPosting jobPosting = new JobPosting();
				jobPosting.setId(rs.getLong("id"));
				jobPosting.setName(rs.getString("name"));
				jobPosting.setDescription(rs.getString("description"));
				jobPosting.setCompany(rs.getString("company"));
				long userId = rs.getLong("user_id");
				User poster = userDao.getObjectById(userId);
				jobPosting.setPoster(poster);

				return jobPosting;
			}
		};
	}

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final JobPosting jobPosting) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into job (id, name) values (?,?) " + "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, jobPosting.getId());
				ps.setString(2, jobPosting.getName());
				return ps;
			}
		};
	}

}
