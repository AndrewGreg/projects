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
import edu.ben.template.model.User;

public class EventDao extends BaseDao<Event> {

	public Event getObjectById(int objectId, boolean complete) {
		if (objectId == 0) {
			/* TODO Probably want to log this */
			return null;
		}
		Event object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM event WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { objectId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* TODO Probably want to log this */
				return null;
			}
		}
		return object;
	}
	
	public ArrayList<Event> findAll() {

		List<Event> events = new ArrayList<Event>();
		String sql = "SELECT * from event";

		try {
			events = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* TODO Probably want to log this */
			return null;
		}
	}
	
	public ArrayList<Event> findByDate(Date date) {

//		TODO
		List<Event> events = new ArrayList<Event>();
		return (ArrayList<Event>) events;
	}
	
	public ArrayList<Event> findByPoster(User user) {

//		TODO
		List<Event> events = new ArrayList<Event>();
		return (ArrayList<Event>) events;
	}
	
//	Returns events by areas of interest they are associated with
	public ArrayList<Event> findByInterest(ArrayList<String> interests) {

//		TODO
		List<Event> events = new ArrayList<Event>();
		return (ArrayList<Event>) events;
	}
	
	//TODO ADD EVENT
	
	@Override
	public RowMapper<Event> getRowMapper() {
		return new RowMapper<Event>() {
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Event event = new Event();
				event.setId(rs.getLong("id"));
				event.setName(rs.getString("name"));
				event.setDate(rs.getDate("date"));
				event.setDescription(rs.getString("description"));
				//TODO get Event Poster through sql on "user" table
				// return the object // userdao sql?
				return event;
			}
		};
	}
	

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Event event) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into event (id, name) values (?,?) "
								+ "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, event.getId());
				ps.setString(2, event.getName());
				return ps;
			}
		};
	}

}
