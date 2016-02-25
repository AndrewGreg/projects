package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import edu.ben.template.model.UploadFile;

public class ImageUploadDao extends BaseDao<UploadFile> {
	
	public ImageUploadDao(){
		super();
	}
	
	public void addImage(UploadFile image) {

		String sql = "INSERT INTO image (id, file) VALUES (?, ?)";

		jdbcTemplate.update(sql,
				new Object[] { image.getId(), image.getData() });

	}
	
	@Override
	public RowMapper<UploadFile> getRowMapper() {
		return new RowMapper<UploadFile>() {
			public UploadFile mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				UploadFile image = new UploadFile();
				image.setId(rs.getLong("id"));
				image.setData( rs.getBytes("file"));
				
				return image;
			}
		};
	}

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final UploadFile image) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into file (id, file) values (?,?) "
								+ "on duplicate key update file = values(file)",
						new String[] { "id" });
				ps.setLong(1, image.getId());
				ps.setBytes(2, image.getData());
				return ps;
			}
		};
	}

}
