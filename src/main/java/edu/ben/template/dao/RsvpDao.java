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

import edu.ben.template.model.Rsvp;


public class RsvpDao extends BaseDao<Rsvp>{

	static final String SEARCH = "SELECT * FROM ";

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

	public ArrayList<Rsvp> getAll() {

		List<Rsvp> rsvp = new ArrayList<Rsvp>();
		String sql = SEARCH + "rsvp WHERE ORDER BY first_name DESC";

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

		String sql = "INSERT INTO rsvp (first_name,last_name,email,role) VALUES (?,?,?,?)";

		jdbcTemplate.update(sql,
				new Object[] { rsvp.getFirstName(), rsvp.getLastName(), rsvp.getEmail(), rsvp.getRole() });
		return;
	}

	/**
	 * Updates the Rsvp and places it back into the database.
	 * 
	 * @param rsvp
	 */
	public void updateRsvp(Rsvp rsvp) {

		String sql = "UPDATE rsvp SET first_name = ?, last_name = ?,email = ?,role = ?,  WHERE id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] { rsvp.getFirstName(), rsvp.getLastName(), rsvp.getEmail(),
					rsvp.getRole(), rsvp.getId() });
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
				rsvp.setId(rs.getLong("id"));
				rsvp.setFirstName(rs.getString("first_name"));
				rsvp.setLastName(rs.getString("last_name"));
				rsvp.setEmail(rs.getString("email"));
				rsvp.setRole(rs.getString("role"));

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
				PreparedStatement ps = connection.prepareStatement(
						"insert into rsvp (id, email) values (?,?) " + "on duplicate key update email = values(email)",
						new String[] { "id" });
				ps.setLong(1, rsvp.getId());
				ps.setString(2, rsvp.getEmail());
				return ps;
			}
		};
	}

}
