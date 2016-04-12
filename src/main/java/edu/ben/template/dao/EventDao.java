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

/**
 * This dao controls the Event object.
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class EventDao extends BaseDao<Event> {

	static final String SEARCH = "SELECT * FROM ";

	// Passes in the User signed in.
	@Autowired
	private UserDao userDao;

	/**
	 * Super constructor
	 */
	public EventDao() {
		super();
	}

	/**
	 * Grabs the id from that belongs to the event. We use this one.
	 * 
	 * @param eventId
	 * @return the Id of the event.
	 */
	public Event getObjectById(long eventId) {
		return this.getObjectById(eventId, false);
	}

	/**
	 * Grabs the Id of the event if it is not null. We don't use this one.
	 * 
	 * @param eventId
	 * @param complete
	 * @return the Id of the event.
	 */
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
				String sql = SEARCH + "event WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { eventId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	/**
	 * Adds the Event to the database.
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {

		String sql = "INSERT INTO event (name, description, date,user_id,public,start_time,end_time, longitude, latitude,role, reference, location) VALUES (?,?,?, ?, ?, ?,?,?, ?, ?, ?,?)";

		jdbcTemplate.update(sql,
				new Object[] { event.getName(), event.getDescription(), event.getDate(), event.getPoster().getId(),
						event.getToPublic(), event.getStartTime(), event.getEndTime(), event.getLongitude(),
						event.getLatitude(), event.getRole(), event.getReference(), event.getLocation() });
		return;
	}

	/**
	 * Updates the Event and places it back into the database.
	 * 
	 * @param event
	 */
	public void updateEvent(Event event) {

		String sql = "UPDATE event SET name = ?, description = ?, date = ?, user_id = ?, public = ?,start_time= ?,end_time= ?, longitude = ?, latitude = ?, role = ?, reference = ?, location = ? WHERE id = ?";
		try {
			jdbcTemplate.update(sql,
					new Object[] { event.getName(), event.getDescription(), event.getDate(), event.getPoster().getId(),
							event.getToPublic(), event.getStartTime(), event.getEndTime(), event.getLongitude(),
							event.getLatitude(), event.getRole(), event.getReference(), event.getLocation(),
							event.getId() });
		} catch (Exception e) {
			/* Probably want to log this */
		}
		return;
	}
	
	public void deleteEvent(Long id){
		
		String sql = "DELETE FROM event WHERE id = ?";
		try{
			jdbcTemplate.update(sql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * List all of the Events by the date. When the date has passed the current date, It will not be displayed..
	 * 
	 * @return all the Events in the database.
	 */
	public ArrayList<Event> getAll() {

		List<Event> events = new ArrayList<Event>();
		String sql = SEARCH + "event WHERE date >= NOW() ORDER BY date DESC";

		try {
			events = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 * @return the name of the event in ascending order.
	 */
	public ArrayList<Event> getByEventSearch(String name) {

		List<Event> events = new ArrayList<Event>();
		String sql = "SELECT name FROM event WHERE name LIKE '%name%' ORDER BY name";

		try {
			// Test this.
			events = jdbcTemplate.query(sql, new Object[] { name }, getRowMapper());

			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			
			return null;
		}
	}

	/**
	 * Retrieve the 5 upcoming events.
	 * 
	 * @param user
	 *            of event.
	 * @return the 5 of upcoming events.
	 */
	public ArrayList<Event> getByUpComingEvent(Date date) {

		List<Event> event = new ArrayList<Event>();
		String sql = SEARCH + "event WHERE event.date is NOT NULL order by event.date ASC Limit 4;";

		try {
			event = jdbcTemplate.query(sql, new Object[] { date }, getRowMapper());

			return (ArrayList<Event>) event;
		} catch (EmptyResultDataAccessException e) {
			
			return null;
		}
	}

	/**
	 * Grabs the Event by the date it was created.
	 * 
	 * @param date
	 * @return the date of the Event.
	 */
	public ArrayList<Event> getByDate(Date date) {

		List<Event> events = new ArrayList<Event>();
		String sql = SEARCH + "event WHERE date = ?";

		try {
			events = jdbcTemplate.query(sql, new Object[] { date }, getRowMapper());// TEST
																					// THIS
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}

	}

	/**
	 * Grabs the user Id who created the Event.
	 * 
	 * @param user
	 * @return the user who posted the Event.
	 */
	public ArrayList<Event> getByPoster(User user) {

		List<Event> events = new ArrayList<Event>();
		String sql = SEARCH + "event WHERE user_id = ?";

		try {
			events = jdbcTemplate.query(sql, new Object[] { user.getId() }, getRowMapper());// TEST
																							// THIS
			return (ArrayList<Event>) events;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}

	}

	/**
	 * Grabs the Event by the interest of the user.
	 * 
	 * @param interest
	 * @return Events by areas of interest they are associated with.
	 */
	public ArrayList<Event> getByInterest(Interest interest) {

		// Returns duplicate rows?
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

	/**
	 * Row Mapper.
	 */
	@Override
	public RowMapper<Event> getRowMapper() {
		return new RowMapper<Event>() {
			@Override
			public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Event event = new Event();
				event.setId(rs.getLong("id"));
				event.setName(rs.getString("name"));
				event.setDate(rs.getDate("date"));
				event.setDescription(rs.getString("description"));
				event.setToPublic(rs.getInt("public"));
				event.setLatitude(rs.getFloat("latitude"));
				event.setLongitude(rs.getFloat("longitude"));
				event.setStartTime(rs.getString("start_time"));
				event.setEndTime(rs.getString("end_time"));
				event.setRole(rs.getInt("role"));
				event.setReference(rs.getString("reference"));
				event.setLocation(rs.getString("location"));

				// Grabs the id of the user that created the event.
				// Displays who posted the event.
				long userId = rs.getLong("user_id");
				User poster = userDao.getObjectById(userId);
				event.setPoster(poster);
				return event;
			}
		};
	}

	/**
	 * Creates the connection to the database.
	 */
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
