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
import edu.ben.template.model.Rsvp;
import edu.ben.template.model.User;

public class RsvpDao extends BaseDao<Rsvp> {

	static final String SEARCH = "SELECT * FROM ";
	// Passes in the User signed in.

	/**
	 * Super constructor
	 */
	public RsvpDao() {
		super();
	}

	/**
	 * Grabs the id from that belongs to the event. We use this one.
	 * 
	 * @param eventId
	 * @return the Id of the event.
	 */
	public Rsvp getObjectById(long rsvpId) {
		return this.getObjectById(rsvpId, false);
	}

	/**
	 * Grabs the Id of the event if it is not null. We don't use this one.
	 * 
	 * @param rsvpId
	 * @param complete
	 * @return the Id of the rsvp.
	 */
	public Rsvp getObjectById(long rsvpId, boolean complete) {
		if (rsvpId == 0) {
			/* Probably want to log this */
			return null;
		}
		Rsvp object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = SEARCH + "rsvp WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { rsvpId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public ArrayList<Rsvp> getAllByFirstName() {

		List<Rsvp> rsvp = new ArrayList<Rsvp>();
		String sql = SEARCH + "rsvp WHERE ORDER BY User.user_Id DESC";

		try {
			rsvp = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Rsvp>) rsvp;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Adds the Rsvp to the database.
	 * 
	 * @param rsvp
	 */
	public void addRsvp(Rsvp rsvp) {

		String sql = "INSERT INTO rsvp (event_id,user_id) VALUES (?,?)";

		jdbcTemplate.update(sql, new Object[] { rsvp.getEventId(), rsvp.getUserId() });
		return;
	}

	/**
	 * Updates the Rsvp and places it back into the database.
	 * 
	 * @param rsvp
	 */
	public void updateRsvp(Rsvp rsvp) {

		String sql = "UPDATE rsvp SET event_id = ?,user_id = ?, WHERE id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] { rsvp.getEventId(), rsvp.getUserId() });
		} catch (Exception e) {
			/* Probably want to log this */
		}
		return;
	}

	public void deleteRsvp(Long id) {

		String sql = "DELETE FROM rsvp WHERE id = ?";
		try {
			jdbcTemplate.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public RowMapper<Rsvp> getRowMapper() {
		return new RowMapper<Rsvp>() {
			@Override
			public Rsvp mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Rsvp rsvp = new Rsvp();
				rsvp.setEventId((Event) rs.getObject("event_id"));
				rsvp.setUserId((User) rs.getObject("user_id"));

				return rsvp;
			}
		};
	}

	/**
	 * Creates the connection to the database.
	 */
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Rsvp rsvp) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("insert into rsvp (id, user_id) values (?,?) "
						+ "on duplicate key update event_id = values(event_id)", new String[] { "id" });
				ps.setLong(1, rsvp.getEventId().getId());
				ps.setString(2, rsvp.getUserId().getEmail());
				return ps;
			}
		};
	}

}
