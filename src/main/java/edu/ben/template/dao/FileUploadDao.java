package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ben.template.model.UploadFile;
import edu.ben.template.model.User;

@Repository
public class FileUploadDao extends BaseDao<UploadFile>{
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	public FileUploadDao(){
		super();
	}
//	public FileUploadDao(SessionFactory sessionFactory) {
//	   this.sessionFactory = sessionFactory;
//	}

	//@Override
	//@Transactional
//	public void save(UploadFile uploadFile) {
//		sessionFactory.getCurrentSession().save(uploadFile);
//	}
	
	
	
	public void addFile(UploadFile file) {

		String sql = "INSERT INTO file (id, file) VALUES (?, ?)";

		jdbcTemplate.update(sql,
				new Object[] { file.getId(), file.getFileName() });

	}
	
	@Override
	public RowMapper<UploadFile> getRowMapper() {
		return new RowMapper<UploadFile>() {
			public UploadFile mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				UploadFile file = new UploadFile();
				file.setId(rs.getLong("id"));
				file.setFileName(rs.getString("first_name"));
				
				
//				TODO See Pollack about structure change (INEFFICIENT)


//				user.setConcentration(majorDao.findConcentrationByUser(user));
//				user.setMinor(majorDao.findMinorByUser(user));
//				user.setMajor(majorDao.findMajorByUser(user));
//				user.setInterest(interestDao.findAllByUser(user));

				// return the object
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
						"insert into file (id, file) values (?,?) "
								+ "on duplicate key update file = values(file)",
						new String[] { "id" });
				ps.setLong(1, file.getId());
				ps.setString(2, file.getFileName());
				return ps;
			}
		};
	}
	
}
