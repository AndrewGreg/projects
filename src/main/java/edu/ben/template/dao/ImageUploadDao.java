package edu.ben.template.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.jdbc.Blob;

import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;
public class ImageUploadDao extends BaseDao<UploadFile> {

	// Passes in the User signed in.
	@Autowired
	private UserDao userDao;
	
	public ImageUploadDao() {
		super();
	}

	public void addImage(UploadFile image) {
		
		String sql = "INSERT INTO image (id, file, user_id) VALUES (?, ?, ?)";
		try{
			jdbcTemplate.update(sql, new Object[] { image.getId(), image.getData(), image.getProfile().getId()});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateImage(UploadFile image){
		String sql = "UPDATE image SET file = ? WHERE user_id = ?";
		try{
			jdbcTemplate.update(sql, new Object[] { image.getData(), image.getProfile().getId() });
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public UploadFile getObjectByUserId(Long id){
		return this.getImageByUserId(id, false);
	}
	
	public UploadFile getImageByUserId(long id,  boolean complete){
		
		if (id == 0) {
			/* Probably want to log this */
			return null;
		}
		UploadFile object = null;	
//		Blob blob = null;
		
		if(object == null){
			try{
				//look up the object
				String sql = "SELECT * FROM image WHERE user_id = ?;";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, getRowMapper());
				
//				String sqlData = "SELECT file FROM image WHERE user_id =?;";
//				blob = this.getJdbcTemplate().queryForObject(sqlData, new Object[] { id }, getRowMapper());
//				
//				InputStream stream = new BufferedInputStream(blob.getBinaryStream());
//				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//				OutputStream output = new BufferedOutputStream(byteArrayOutputStream);
//				int i;
//				while (-1 != (i = stream.read())) {
//					output.write(i);
//				}
//				output.flush();
//				object.setData(byteArrayOutputStream.toByteArray());
				//return byteArrayOutputStream.toByteArray();
				
//				InputStream stream = new BufferedInputStream(pic.getBinaryStream());
//				ByteArrayOutputStream byteArrayOutputStream = new 
//						return profilePic;
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return object;
	}

	
	public UploadFile getObjectById(Long id) {
		return this.getImageById(id, false);
	}

	public UploadFile getImageById(Long id,  boolean complete){
		
		if (id == 0) {
			/* Probably want to log this */
			return null;
		}
		UploadFile object = null;
		
		if(object == null){
			try{
				//look up the object
				String sql = "SELECT * FROM image WHERE id = ?;";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, getRowMapper());
//				InputStream stream = new BufferedInputStream(pic.getBinaryStream());
//				ByteArrayOutputStream byteArrayOutputStream = new 
//						return profilePic;
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return object;
	}
	

	@Override
	public RowMapper<UploadFile> getRowMapper() {
		return new RowMapper<UploadFile>() {
			@Override
			public UploadFile mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				UploadFile image = new UploadFile();
				image.setId(rs.getLong("id"));
				image.setData(rs.getBytes("file"));
				
				// Grabs the id of the user of the profile pic.
				// Displays pic of user.
				long userId = rs.getLong("user_id");
				User profile = userDao.getObjectById(userId);
				image.setProfile(profile);
				return image;
			}
		};
	}
	
//	public RowMapper<Blob> getRowMapper() {
//		return new RowMapper<UploadFile>() {
//			@Override
//			public UploadFile mapRow(ResultSet rs, int rowNum) throws SQLException {
//				// map result set to object
//				UploadFile image = new UploadFile();
//				image.setId(rs.getLong("id"));
//				image.setData(rs.getBytes("file"));
//				
//				// Grabs the id of the user of the profile pic.
//				// Displays pic of user.
//				long userId = rs.getLong("user_id");
//				User profile = userDao.getObjectById(userId);
//				image.setProfile(profile);
//				return image;
//			}
//		};
//	}

	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final UploadFile image) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into file (id, file) values (?,?,?) " + "on duplicate key update file = values(file)",
						new String[] { "id" });
				ps.setLong(1, image.getId());
				ps.setBinaryStream(2, new ByteArrayInputStream(image.getData()));
				return ps;
			}
		};
	}

}
