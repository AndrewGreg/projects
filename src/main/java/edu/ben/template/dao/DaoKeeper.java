package edu.ben.template.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;

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
	private JobDao jobPostingDao;
	@Autowired
	private MajorDao majorDao;
	@Autowired
	private TitleDao titleDao;
	@Autowired
	private ReasonDao reasonDao;
	@Autowired
	private TransferDao transferDao;
	@Autowired
	private InterestDao interestDao;
	@Autowired
	private FileUploadDao fileUploadDao;
	@Autowired
	private ImageUploadDao imageUploadDao;

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

	public JobDao getJobPostingDao() {
		return jobPostingDao;
	}

	public void setJobPostingDao(JobDao jobPostingDao) {
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

	public ImageUploadDao getImageUploadDao() {
		return imageUploadDao;
	}

	public void setimageUploadDao(ImageUploadDao imageUploadDao) {
		this.imageUploadDao = imageUploadDao;
	}

	public InterestDao getInterestDao() {
		return interestDao;
	}

	public void setInterestDao(InterestDao interestDao) {
		this.interestDao = interestDao;
	}

	public TitleDao getTitleDao() {
		return titleDao;
	}

	public void setTitleDao(TitleDao titleDao) {
		this.titleDao = titleDao;
	}

	public ReasonDao getReasonDao() {
		return reasonDao;
	}

	public void setReasonDao(ReasonDao reasonDao) {
		this.reasonDao = reasonDao;
	}

	public TransferDao getTransferDao() {
		return transferDao;
	}

	public void setTransferDao(TransferDao transferDao) {
		this.transferDao = transferDao;
	}

	public void setImageUploadDao(ImageUploadDao imageUploadDao) {
		this.imageUploadDao = imageUploadDao;
	}

}
