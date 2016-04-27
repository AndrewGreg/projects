package edu.ben.template.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.JobDao;
import edu.ben.template.model.Job;
import edu.ben.template.model.User;
import junit.framework.AssertionFailedError;

public class JobTest {

	@Autowired
	private JobDao jobDao = new JobDao();

	
	// @Test
	// @Transactional
	// @Rollback(true)
	// public void test() {
	// assert.assertEquals();//Assert.assertEquals(null, null);
	// }

	// @org.junit.Test
	// @Transactional
	// @Rollback(true)
	// public void testGetters() {
	//
	// jobDao.setDataSource(getDataSource());
	// jobDao.setTransactionManager(getTransactionManager());
	// interestDao.setDataSource(getDataSource());
	// interestDao.setTransactionManager(getTransactionManager());
	//
	// Date d = new Date(6);
	// User user = new User();
	// user.setId((long) 1);
	// Job job = new Job("Developer", "Code until you can't anymore",
	// "Microsoft", user, "Chicago", "something", 1, 1,
	// "link");
	//
	// Interest interest = new Interest();
	// interest.setId((long) 1);
	// User u = new User();
	// u.setId((long) 1);
	// job.setPoster(u);
	// interestDao.addInterestToJobPosting(job, interest);
	//
	// try {
	// jobDao.addJob(job);
	//
	// } catch (Exception e) {
	// throw new AssertionFailedError("addJob() failed");
	//
	// }
	//
	// try {
	//
	// if (jobDao.getAll() == null) {
	// throw new AssertionFailedError("getAll() Failed");
	// }
	//
	// } catch (Exception e) {
	// throw new AssertionFailedError("getAll() Failed");
	// }
	//
	// try {
	//
	// if (jobDao.getByPoster(u) == null) {
	// throw new AssertionFailedError("getByPoster() Failed");
	// }
	//
	// } catch (Exception e) {
	// throw new AssertionFailedError("getByPoster() Failed");
	// }
	//
	// try {
	//
	// if (jobDao.getByInterest(interest) == null) {
	// throw new AssertionFailedError("getByInterest() Failed");
	// }
	//
	// } catch (Exception e) {
	// throw new AssertionFailedError("getByInterest() Failed");
	// }
	// }

	@Test
	@Transactional
	@Rollback(true)
	public void testGetObjectByID() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);
		Job job = new Job("Developer", "Code on the object Id.", "Microsoft", user, "Chicago", "something", 1, 1,
				"link", true, 0, 0, 26, 2000);
		job.setPoster(user);

		try {
			jobDao.addJob(job);

		} catch (Exception e) {
			throw new AssertionFailedError("addJob() failed");

		}

		try {
			jobDao.getObjectById(0);

		} catch (Exception e) {
			throw new AssertionFailedError("getObjectById() Failed");
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testInsertJob() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);
		Job job = new Job("Developer", "Code for the insert job", "Microsoft", user, "Chicago", "something", 1, 1,
				"link", true, 0, 0, 25, 2000);
		job.setPoster(user);

		try {
			jobDao.addJob(job);

		} catch (Exception e) {
			throw new AssertionFailedError("addJob() failed");

		}

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateJob() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);
		Job job = new Job("Developer", "Code on the Update", "Microsoft", user, "Chicago", "something", 1, 1, "link",
				true, 0, 0, 24, 2000);
		job.setPoster(user);

		try {
			jobDao.addJob(job);

		} catch (Exception e) {
			throw new AssertionFailedError("addJob() failed");

		}

		User user2 = new User();
		user2.setId((long) 1000);
		job.setPoster(user2);

		try {

			jobDao.updateJob(job);

			if (jobDao.getByPoster(user2) == null) {
				throw new AssertionFailedError("updateJob() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("updateJob() Failed");
		}

	}

	// @Test
	// @Transactional
	// @Rollback(true)
	// public void testGetSearchByJobName() {
	//
	// jobDao.setDataSource(getDataSource());
	// jobDao.setTransactionManager(getTransactionManager());
	//
	// User user = new User();
	// user.setId((long) 1);
	// Job job = new Job("Developer", "Code until you can't anymore",
	// "Microsoft", user, "Chicago", "something", 1, 1,
	// "link");
	// Job job2 = new Job("Web", "Code until you can't anymore", "Microsoft",
	// user, "Chicago", "something", 1, 1,
	// "link");
	// Job job3 = new Job("Mobile Application", "Code until you can't anymore",
	// "Microsoft", user, "Chicago", "something", 1, 1,
	// "link");
	//
	// try {
	// jobDao.addJob(job);
	// jobDao.addJob(job2);
	// jobDao.addJob(job3);
	//
	// } catch (Exception e) {
	// throw new AssertionFailedError("addJob() failed");
	//
	// }
	//
	// try {
	//
	// jobDao.getSearchByJobName(job2.getName());
	//
	// if (jobDao.getSearchByJobName("Web") == null) {
	// throw new AssertionFailedError("SearchByJobName() Failed");
	// }
	//
	// } catch (Exception e) {
	// throw new AssertionFailedError("SearchByJobName() Failed");
	// }
	//
	// }

	@Test
	@Transactional
	@Rollback(true)
	public void testGetByHighestPaidWage() {

		jobDao.setDataSource(getDataSource());
		jobDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);

		ArrayList<Job> jobs = new ArrayList<Job>();

		Job job = new Job("Developer", "Code A Lot", "Microsoft", user, "Chicago", "something", 1, 1, "link", true, 0,
				0, 1000, 2000);
		Job job2 = new Job("Web", "Code until you can't anymore", "Windows", user, "Chicago", "something", 1, 1, "link",
				true, 0, 0, 50, 2000);
		Job job3 = new Job("Mobile Application", "Code sometimes", "Apple", user, "Chicago", "something", 1, 1, "link",
				true, 0, 0, 49, 2000);
		Job job4 = new Job("Mobile Application", "Code a little", "Linux", user, "Chicago", "something", 1, 1, "link",
				true, 0, 0, 30, 2000);
		job.setPoster(user);
		job2.setPoster(user);
		job3.setPoster(user);
		job4.setPoster(user);

		jobs.add(job);
		jobs.add(job2);
		jobs.add(job3);
		jobs.add(job4);

		try {

			jobDao.addJob(job);
			jobDao.addJob(job2);
			jobDao.addJob(job3);
			jobDao.addJob(job4);

			if (jobDao.getByPoster(user) == null) {
				throw new AssertionFailedError("updateJob() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("updateJob() Failed");
		}

		ArrayList<Job> expected = jobDao.getByHighestPaidWage();
		assertEquals(expected, jobs);

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
