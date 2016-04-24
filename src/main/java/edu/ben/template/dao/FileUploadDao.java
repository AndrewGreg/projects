package edu.ben.template.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.mysql.jdbc.Blob;

import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;

@Repository
public class FileUploadDao extends BaseDao<UploadFile>{
	
	@Autowired
	private UserDao userDao;
	
	public FileUploadDao(){
		super();
	}

	public void addFile(UploadFile file) {

		String sql = "INSERT INTO file (id, file, user_id) VALUES (?, ?, ?);";

		jdbcTemplate.update(sql,
				new Object[] { file.getId(), file.getData(), file.getProfile().getId() });

	}
	
	public void updateFile(UploadFile file){
		String sql = "UPDATE image SET file = ? WHERE user_id = ?";
		try{
			jdbcTemplate.update(sql, new Object[] { file.getData(), file.getProfile().getId() });
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public UploadFile getObjectByUserId(Long id){
		return this.getFileByUserId(id, false);
	}
	
	public UploadFile getFileByUserId(long id,  boolean complete){
		
		if (id == 0) {
			/* Probably want to log this */
			return null;
		}
		UploadFile object = null;	
		
		if(object == null){
			try{
				//look up the object
				String sql = "SELECT * FROM file WHERE user_id = ?;";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, getRowMapper());

			}catch(EmptyResultDataAccessException e){
				return null;
			}
		}
		return object;
	}
	
	public UploadFile getObjectById(Long id) {
		return this.getFileById(id, false);
	}

	public UploadFile getFileById(Long id,  boolean complete){
		
		if (id == 0) {
			/* Probably want to log this */
			return null;
		}
		UploadFile object = null;
		
		if(object == null){
			try{
				//look up the object
				String sql = "SELECT * FROM file WHERE id = ?;";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { id }, getRowMapper());

			
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
				UploadFile file = new UploadFile();
				file.setId(rs.getLong("id"));
				file.setData(rs.getBytes("file"));
				

				// Grabs the id of the user of the profile pic.
				// Displays pic of user.
				long userId = rs.getLong("user_id");
				User profile = userDao.getObjectById(userId);
				file.setProfile(profile);
				return file;
			}
		};
	}
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final UploadFile file) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into file (id, file) values (?,?,?) "
								+ "on duplicate key update file = values(file)",
						new String[] { "id" });
				ps.setLong(1, file.getId());
				ps.setBinaryStream(2, new ByteArrayInputStream(file.getData()));
				return ps;
			}
		};
	}
	
}
