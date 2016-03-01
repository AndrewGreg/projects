package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.Date;
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
import edu.ben.template.model.User;

public class EventDao extends BaseDao<Event> {

	@Autowired
	private UserDao userDao;
	
	public EventDao() {
		super();
	}

	public Event getObjectById(long eventId) {
		return this.getObjectById(eventId, false);
	}

	public Event getObjectById(long eventId, boolean complete) {
		if (eventId == 0) {
			/* Probably want to log this */
			return null;
		}
		Event object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM event WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { eventId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public ArrayList<Event> getAll() {

		List<Event> events = new ArrayList<Event>();
		String sql = "SELECT * from event";

		try {
			events = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Event> getByDate(Date date) {

		List<Event> events = new ArrayList<Event>();
		String sql = "SELECT * from event WHERE date = ?";

		try {
			events = jdbcTemplate.query(sql, new Object[] { date }, getRowMapper());// TEST
																					// THIS
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}

	}

	public ArrayList<Event> getByPoster(User user) {

		List<Event> events = new ArrayList<Event>();
		String sql = "SELECT * from event WHERE user_id = ?";

		try {
			events = jdbcTemplate.query(sql, new Object[] { user.getId() }, getRowMapper());// TEST
																							// THIS
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}

	}

	// Returns events by areas of interest they are associated with
	public ArrayList<Event> getByInterest(Interest interest) {

		//Returns duplicate rows?
		String sql = "SELECT e.id, e.name, e.date, e.description FROM  event_interest ei JOIN event e on ei.event_id = e.id JOIN interest i on ei.event_id = i.id WHERE i.id = ?";
		List<Event> events = new ArrayList<Event>();
		
		try {
			events = jdbcTemplate.query(sql, new Object[] { interest.getId() }, getRowMapper());// TEST
																							// THIS
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public void addEvent(Event event) {

		String sql = "INSERT INTO event (name, description, date, user_id, hidden) VALUES (?, ?, ?, ?, 0)";

		jdbcTemplate.update(sql,
				new Object[] { event.getName(), event.getDescription(), event.getDate(), event.getPoster().getId() });
		return;
	}

	public void updateEvent(Event event) {

		String sql = "UPDATE event SET name = ?, description = ?, date = ?, user_id = ? WHERE id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] { event.getName(), event.getDescription(), event.getDate(),
					event.getPoster().getId(), event.getId() });
		} catch (Exception e) {
			/* Probably want to log this */
		}
		return;
	}

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
						"insert into event (id, name) values (?,?) " + "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, event.getId());
				ps.setString(2, event.getName());
				return ps;
			}
		};
	}

}
