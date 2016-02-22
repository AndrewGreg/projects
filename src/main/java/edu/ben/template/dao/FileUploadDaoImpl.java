package edu.ben.template.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.ben.template.model.UploadFile;

@Repository
public class FileUploadDaoImpl implements FileUploadDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public FileUploadDaoImpl(){
		
	}
	public FileUploadDaoImpl(SessionFactory sessionFactory) {
	   this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(UploadFile uploadFile) {
		sessionFactory.getCurrentSession().save(uploadFile);
	}
	
}
