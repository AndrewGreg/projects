package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import edu.ben.template.model.Event;
import edu.ben.template.model.JobPosting;
import edu.ben.template.model.User;

public class JobPostingDao extends BaseDao<JobPosting> {

	public JobPosting getObjectById(int objectId, boolean complete) {
		if (objectId == 0) {
			/* TODO Probably want to log this */
			return null;
		}
		JobPosting object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM job WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { objectId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* TODO Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public ArrayList<JobPosting> findAll() {

		List<JobPosting> events = new ArrayList<JobPosting>();
		String sql = "SELECT * from job";

		try {
			events = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<JobPosting>) events;
		} catch (EmptyResultDataAccessException e) {
			/* TODO Probably want to log this */
			return null;
		}
	}

	public ArrayList<Event> findByPoster(User user) {

		// TODO
		ArrayList<Event> events = new ArrayList<Event>();
		return events;
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
				// TODO get Job Poster through sql on "user" table
				// return the object
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
