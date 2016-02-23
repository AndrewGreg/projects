package edu.ben.template.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * this is here to just hold all the dao objects
 */
@Import({ DaoConfig.class })
public class DaoKeeper {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EventDao eventDao;
	@Autowired
	private JobPostingDao jobPostingDao;
	@Autowired
	private MajorDao majorDao;
	@Autowired
	private InterestDao interestDao;
	@Autowired
	private FileUploadDao fileUploadDao;
	

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public EventDao getEventDao() {
		return eventDao;
	}

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	public JobPostingDao getJobPostingDao() {
		return jobPostingDao;
	}

	public void setJobPostingDao(JobPostingDao jobPostingDao) {
		this.jobPostingDao = jobPostingDao;
	}

	public MajorDao getMajorDao() {
		return majorDao;
	}

	public void setMajorDao(MajorDao majorDao) {
		this.majorDao = majorDao;
	}
	
	public FileUploadDao getFileUploadDao() {
		return fileUploadDao;
	}

	public void setFileUploadDao(FileUploadDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}

	public InterestDao getInterestDao() {
		return interestDao;
	}

	public void setInterestDao(InterestDao interestDao) {
		this.interestDao = interestDao;
	}

}
