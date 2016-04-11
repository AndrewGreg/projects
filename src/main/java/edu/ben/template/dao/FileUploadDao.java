package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.mysql.jdbc.Blob;

import edu.ben.template.model.UploadFile;

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

		String sql = "INSERT INTO file (id, file) VALUES (?, ?);";

		jdbcTemplate.update(sql,
				new Object[] { file.getId(), file.getData() });

	}
	
	
	@Override
	public RowMapper<UploadFile> getRowMapper() {
		return new RowMapper<UploadFile>() {
			@Override
			public UploadFile mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				UploadFile file = new UploadFile();
				file.setId(rs.getLong("id"));
				file.setData((Blob) rs.getBlob("file"));
				
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
				ps.setBlob(2, file.getData());
				return ps;
			}
		};
	}
	
}
