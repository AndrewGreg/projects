package edu.ben.template.test;

import java.sql.Date;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.EventDao;
import edu.ben.template.dao.InterestDao;
import edu.ben.template.model.Event;
import edu.ben.template.model.Interest;
import edu.ben.template.model.User;
import junit.framework.AssertionFailedError;

public class EventTest {

	@Autowired
	private EventDao eventDao = new EventDao();
	@Autowired
	private InterestDao interestDao = new InterestDao();

	// @SuppressWarnings("deprecation")
	// @Test
	// @Transactional
	// @Rollback(true)
	// public void test() {
	// Assert.assertEquals(null, null);
	// }

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertRecords() {

		eventDao.setDataSource(getDataSource());
		eventDao.setTransactionManager(getTransactionManager());

		Event event = new Event("Title", new Date(6), "Description");
		User u = new User();
		u.setId((long) 1);
		event.setPoster(u);

		try {
			eventDao.addEvent(event);

		} catch (Exception e) {
			throw new AssertionFailedError("addEvent() failed");

		}

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetters() {

		eventDao.setDataSource(getDataSource());
		eventDao.setTransactionManager(getTransactionManager());
		interestDao.setDataSource(getDataSource());
		interestDao.setTransactionManager(getTransactionManager());

		Date d = new Date(6);
		Event event = new Event("Title", d, "Description");
		Interest interest = new Interest();
		interest.setId((long) 1);
		User u = new User();
		u.setId((long) 1);
		event.setPoster(u);
		interestDao.addInterestToEvent(event, interest);

		try {
			eventDao.addEvent(event);

		} catch (Exception e) {
			throw new AssertionFailedError("addEvent() failed");

		}

		try {
			eventDao.addEvent(event);

		} catch (Exception e) {
			throw new AssertionFailedError("addEvent() failed");

		}

		try {

			if (eventDao.getAll() == null) {
				throw new AssertionFailedError("getAll() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAll() Failed");
		}

		try {

			if (eventDao.getByPoster(u) == null) {
				throw new AssertionFailedError("getByPoster() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByPoster() Failed");
		}

		try {

			if (eventDao.getByDate(d) == null) {
				throw new AssertionFailedError("getByDate() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByDate() Failed");
		}

		try {

			if (eventDao.getByInterest(interest) == null) {
				throw new AssertionFailedError("getByInterest() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByInterest() Failed");
		}

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateEvent() {

		eventDao.setDataSource(getDataSource());
		eventDao.setTransactionManager(getTransactionManager());

		Event event = new Event("Title", new Date(6), "Description");
		User u = new User();
		u.setId((long) 1);
		event.setPoster(u);

		try {
			eventDao.addEvent(event);

		} catch (Exception e) {
			throw new AssertionFailedError("addEvent() failed");

		}

		Date d2 = new Date(10);
		event.setDate(d2);

		try {

			eventDao.updateEvent(event);

			if (eventDao.getByDate(d2) == null) {
				throw new AssertionFailedError("getByDate() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByDate() Failed");
		}

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetObjectByID() {

		eventDao.setDataSource(getDataSource());
		eventDao.setTransactionManager(getTransactionManager());

		Event event = new Event("Title", new Date(6), "Description");
		User u = new User();
		u.setId((long) 1);
		event.setPoster(u);

		try {
			eventDao.addEvent(event);

		} catch (Exception e) {
			throw new AssertionFailedError("addEvent() failed");

		}

		try {

			if (eventDao.getObjectById(1) == null) {
				throw new AssertionFailedError("getObjectById() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getObjectById() Failed");
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
