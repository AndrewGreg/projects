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

import edu.ben.template.model.Title;
import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;

public class TitleDao extends BaseDao<Title> {

	public TitleDao() {
		super();
	}

	public Title getObjectById(long titleId) {
		return this.getObjectById(titleId, false);
	}

	public Title getObjectById(long titleId, boolean complete) {
		if (titleId == 0) {
			/* Probably want to log this */
			return null;
		}
		Title object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM title WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { titleId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}
	
	public Title getObjectByName(String name) {
		if (name.equals(null) || name == "") {
			/* Probably want to log this */
			return null;
		}
		Title object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM title WHERE name = ? LIMIT 1";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { name }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	public void addTitle(String title) {

		// Created, lastModified, lastLogin, lastActive will be implemented in
		// future sprint
		String sql = "INSERT INTO title (name) VALUES (?);";
		
		jdbcTemplate.update(sql,
				new Object[] { title });

	}

	public ArrayList<Title> getAll() {

		List<Title> titles = new ArrayList<Title>();
		String sql = "SELECT * from title";

		try {
			titles = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<Title>) titles;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}


	public void updateTitle(Title title) {

		String sql = "UPDATE title SET `name`=? WHERE `id`=?;";

		try {
			jdbcTemplate.update(sql,
					new Object[] { title.getName(),title.getId() });
		} catch (Exception e) {
			/* Probably want to log this */
		}
		return;

	}

	@Override
	public RowMapper<Title> getRowMapper() {
		return new RowMapper<Title>() {
			public Title mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Title t = new Title();
				t.setName(rs.getString("name"));
				t.setId(rs.getInt("id"));

				return t;
			}
		};
	}

	
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Title title) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into title (id, name) values (?,?) "
								+ "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, title.getId());
				ps.setString(2, title.getName());
				return ps;
			}
		};
	}
}
