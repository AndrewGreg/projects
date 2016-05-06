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

import edu.ben.template.model.Testimonial;
import edu.ben.template.model.User;

public class TestimonialDao extends BaseDao<Testimonial> {

	static final String SEARCH = "SELECT * FROM ";

	// Passes in the User signed in.
	@Autowired
	private UserDao userDao;

	/**
	 * Super constructor
	 */
	public TestimonialDao() {
		super();
	}

	/**
	 * Grabs the id from that belongs to the testimonial. We use this one.
	 * 
	 * @param testimonialId
	 * @return the Id of the testimonial.
	 */
	public Testimonial getObjectById(long testimonialId) {
		return this.getObjectById(testimonialId, false);
	}

	/**
	 * Grabs the Id of the testimonial if it is not null. We don't use this one.
	 * 
	 * @param testimonialId
	 * @param complete
	 * @return the Id of the testimonial.
	 */
	public Testimonial getObjectById(long testimonialId, boolean complete) {
		if (testimonialId == 0) {
			/* Probably want to log this */
			return null;
		}
		Testimonial object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = SEARCH + "testimonial WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { testimonialId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}
	
	

	/**
	 * Adds the Testimonial to the database.
	 * 
	 * @param event
	 */
	public void addTestimonial(Testimonial testimonial) {

		String sql = "INSERT INTO testimonial (testimonial,user_id) VALUES (?,?)";

		jdbcTemplate.update(sql, new Object[] { testimonial.getTestimonial(), testimonial.getPoster().getId() });
		return;
	}

	/**
	 * Updates the Testimonial and places it back into the database.
	 * 
	 * @param event
	 */
	public void updateTestimonial(Testimonial testimonial) {

		String sql = "UPDATE testimonial SET testimonial = ? WHERE id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] { testimonial.getTestimonial(), testimonial.getId() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public void deleteTestimonial(Long id) {

		String sql = "DELETE FROM testimonial WHERE id = ?";
		try {
			jdbcTemplate.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * List all of the Events by the date. When the date has passed the current
	 * date, It will not be displayed..
	 * 
	 * @return all the Events in the database.
	 */
	public ArrayList<Testimonial> getAll() {

		List<Testimonial> testimonial = new ArrayList<Testimonial>();
		String sql = SEARCH
				+ "testimonial join user WHERE user.id = testimonial.user_id AND active = 1 AND hidden = 0 ORDER BY testimonial DESC";

		try {
			testimonial = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Testimonial>) testimonial;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Grabs the user Id who created the testimonial.
	 * 
	 * @param user
	 * @return the user who posted the testimonial.
	 */
	public ArrayList<Testimonial> getByPoster(User user) {

		List<Testimonial> testimonial = new ArrayList<Testimonial>();
		String sql = SEARCH + "testimonial WHERE user_id = ?";

		try {
			testimonial = jdbcTemplate.query(sql, new Object[] { user.getId() }, getRowMapper());

			return (ArrayList<Testimonial>) testimonial;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}

	}

	@Override
	public RowMapper<Testimonial> getRowMapper() {
		return new RowMapper<Testimonial>() {
			@Override
			public Testimonial mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Testimonial testimonial = new Testimonial();
				testimonial.setId(rs.getLong("id"));
				testimonial.setTestimonial(rs.getString("testimonial"));

				// Grabs the id of the user that created the testimonial.
				// Displays who posted the testimonial.
				long userId = rs.getLong("user_id");
				User poster = userDao.getObjectById(userId);
				testimonial.setPoster(poster);
				return testimonial;
			}
		};
	}

	/**
	 * Creates the connection to the database.
	 */
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Testimonial testimonial) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement("insert into testimonial (id, testimonial) values (?,?) "
								+ "on duplicate key update user_id = values(user_id)", new String[] { "id" });
				ps.setLong(1, testimonial.getId());
				ps.setString(2, testimonial.getTestimonial());
				return ps;
			}
		};
	}

}
