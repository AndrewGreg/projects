package edu.ben.dao.test.junit;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.InterestDao;
import edu.ben.template.model.Event;
import edu.ben.template.model.Interest;
import edu.ben.template.model.Job;
import edu.ben.template.model.User;
import junit.framework.AssertionFailedError;

public class InterestDaoTest {

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
	public void testInserts() {

		interestDao.setDataSource(getDataSource());
		interestDao.setTransactionManager(getTransactionManager());

		Interest interest = new Interest("test");

		try {
			interestDao.addInterest(interest);

		} catch (Exception e) {
			throw new AssertionFailedError("addInterest() failed");

		}

		interest.setId((long) 1);
		User user = new User();
		user.setId((long) 1);

		try {
			interestDao.addInterestToUser(user, interest);

		} catch (Exception e) {
			throw new AssertionFailedError("addInterestToUser() failed");
		}

		Event event = new Event();
		event.setId((long) 1);
		try {
			interestDao.addInterestToEvent(event, interest);

		} catch (Exception e) {
			throw new AssertionFailedError("addInterestToEvent() failed");
		}

		Job job = new Job();
		job.setId((long) 1);
		try {
			interestDao.addInterestToJobPosting(job, interest);

		} catch (Exception e) {
			throw new AssertionFailedError("addInterestToJobPosting() failed");
		}
	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testGetObjectByID() {

		interestDao.setDataSource(getDataSource());
		interestDao.setTransactionManager(getTransactionManager());

		Interest interest = new Interest("test");

		try {
			interestDao.addInterest(interest);

		} catch (Exception e) {
			throw new AssertionFailedError("addInterest() failed");

		}

		try {

			if (interestDao.getObjectById(1) == null) {
				throw new AssertionFailedError("getObjectById() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getObjectById() Failed");
		}
	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testGetters() {

		interestDao.setDataSource(getDataSource());
		interestDao.setTransactionManager(getTransactionManager());

		Interest interest = new Interest("test");

		interestDao.addInterest(interest);

		interest.setId((long) 1);
		User user = new User();
		user.setId((long) 1);

		interestDao.addInterestToUser(user, interest);

		Event event = new Event();
		event.setId((long) 1);
		interestDao.addInterestToEvent(event, interest);

		Job job = new Job();
		job.setId((long) 1);
		interestDao.addInterestToJobPosting(job, interest);
		
		
		try {
			if (interestDao.getAll() == null){
				throw new AssertionFailedError("getAll() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAll() failed");

		}
		
		try {
			if (interestDao.getAllByUser(user) == null){
				throw new AssertionFailedError("getAllByUser() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAllByUser() failed");

		}
		
		try {
			if (interestDao.getAllByEvent(event) == null){
				throw new AssertionFailedError("getAllByEvent() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAllByEvent() failed");

		}
		
		try {
			if (interestDao.getAllByJobPosting(job) == null){
				throw new AssertionFailedError("getAllByJobPosting() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAllByJobPosting() failed");

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
