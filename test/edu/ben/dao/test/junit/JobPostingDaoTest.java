package edu.ben.dao.test.junit;

import java.sql.Date;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.InterestDao;
import edu.ben.template.dao.jobDao;
import edu.ben.template.dao.UserDao;
import edu.ben.template.model.Event;
import edu.ben.template.model.Interest;
import edu.ben.template.model.job;
import edu.ben.template.model.User;
import junit.framework.AssertionFailedError;

public class JobPostingDaoTest {

	@Autowired
	private JobDao jobDao = new JobDao();
	@Autowired
	private InterestDao interestDao = new InterestDao();

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void test() {
		Assert.assertEquals(null, null);
	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testGetters() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());
		interestDao.setDataSource(getDataSource());
		interestDao.setTransactionManager(getTransactionManager());

		Date d = new Date(6);
		User user = new User();
		user.setId((long) 1);
		job job = new job("Developer", "Code Monkey", "BU", user);
		Interest interest = new Interest();
		interest.setId((long) 1);
		User u = new User();
		u.setId((long) 1);
		job.setPoster(u);
		interestDao.addInterestToJobPosting(job, interest);

		try {
			jobDao.addJobPosting(job);

		} catch (Exception e) {
			throw new AssertionFailedError("addJobPosting() failed");

		}

		try {

			if (jobDao.getAll() == null) {
				throw new AssertionFailedError("getAll() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAll() Failed");
		}

		try {

			if (jobDao.getByPoster(u) == null) {
				throw new AssertionFailedError("getByPoster() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByPoster() Failed");
		}

		try {

			if (jobDao.getByInterest(interest) == null) {
				throw new AssertionFailedError("getByInterest() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByInterest() Failed");
		}
	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testGetObjectByID() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);
		job job = new job("Developer", "Code Monkey", "BU", user);

		try {
			jobDao.addJobPosting(job);

		} catch (Exception e) {
			throw new AssertionFailedError("addJob() failed");

		}

		try {

			if (jobDao.getObjectById(1) == null) {
				throw new AssertionFailedError("getObjectById() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getObjectById() Failed");
		}
	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testInsertRecords() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);
		job job = new job("Developer", "Code Monkey", "BU", user);

		try {
			jobDao.addJobPosting(job);

		} catch (Exception e) {
			throw new AssertionFailedError("addJob() failed");

		}

	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testUpdateJobPosting() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);
		job job = new job("Developer", "Code Monkey", "BU", user);

		try {
			jobDao.addJobPosting(job);

		} catch (Exception e) {
			throw new AssertionFailedError("addJob() failed");

		}

		User user2 = new User();
		user2.setId((long) 1000);
		job.setPoster(user2);

		try {

			jobDao.updateJobPosting(job);

			if (jobDao.getByPoster(user2) == null) {
				throw new AssertionFailedError("updateJob() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("updateJob() Failed");
		}

	}

	// turn to bean? How to use in DaoConfig rather than here... Injection not
	// working here
	public DataSource getDataSource() {
		// create the data source (use the built in tomcat one)
		BasicDataSource dataSource = new BasicDataSource();
		// if (EnvUtil.isProduction()) {
		// dataSource.setDriverClassName("com.mysql.jdbc.GoogleDriver");
		// dataSource.setUrl("jdbc:google:mysql://database");
		// } else {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/alumnitracker");
		// }
		// org.apache.tomcat.jdbc.pool.DataSource dataSource = new
		// org.apache.tomcat.jdbc.pool.DataSource();
		// dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		// dataSource.setUrl("jdbc:mysql://localhost:3306/dbname");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setValidationQuery("select id from user limit 1");
		// dataSource.setValidationInterval(30000);
		// dataSource.setLogValidationErrors(true);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setRemoveAbandoned(true);
		dataSource.setLogAbandoned(true);
		return dataSource;
	}

	public DataSourceTransactionManager getTransactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(getDataSource());
		return transactionManager;
	}
}
