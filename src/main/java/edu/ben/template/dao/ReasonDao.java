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

import edu.ben.template.model.Reason;
import edu.ben.template.model.Title;
import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;

public class ReasonDao extends BaseDao<Reason> {

	public ReasonDao() {
		super();
	}

	public Reason getObjectById(long reasonId) {
		return this.getObjectById(reasonId, false);
	}

	public Reason getObjectById(long reasonId, boolean complete) {
		if (reasonId == 0) {
			/* Probably want to log this */
			return null;
		}
		Reason object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM reason WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { reasonId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public Reason getObjectByName(String name) {
		if (name.equals(null) || name == "") {
			/* Probably want to log this */
			return null;
		}
		Reason object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM reason WHERE name = ? LIMIT 1";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { name }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public void addReason(String reason) {

		// Created, lastModified, lastLogin, lastActive will be implemented in
		// future sprint
		String sql = "INSERT INTO reason (name) VALUES (?);";

		jdbcTemplate.update(sql, new Object[] { reason });

	}

	public ArrayList<Reason> getAll() {

		List<Reason> reasons = new ArrayList<Reason>();
		String sql = "SELECT * from reason";

		try {
			reasons = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<Reason>) reasons;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public void updateTitle(Reason reason) {

		String sql = "UPDATE reason SET `name`=? WHERE `id`=?;";

		try {
			jdbcTemplate.update(sql, new Object[] { reason.getName(), reason.getId() });
		} catch (Exception e) {
			/* Probably want to log this */
		}
		return;

	}

	@Override
	public RowMapper<Reason> getRowMapper() {
		return new RowMapper<Reason>() {
			public Reason mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Reason r = new Reason();
				r.setName(rs.getString("name"));
				r.setId(rs.getInt("id"));

				return r;
			}
		};
	}

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Reason reason) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into reason (id, name) values (?,?) " + "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, reason.getId());
				ps.setString(2, reason.getName());
				return ps;
			}
		};
	}
}