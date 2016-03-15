package edu.ben.dao.test.junit;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.UserDao;
import edu.ben.template.model.User;
import junit.framework.AssertionFailedError;

public class UserDaoTest {

	@Autowired
	private UserDao userDao = new UserDao();

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void test() {
		Assert.assertEquals(null, null);
	}

//	@org.junit.Test
//	@Transactional
//	@Rollback(true)
//	public void testInserts() {
//
//		userDao.setDataSource(getDataSource());
//		userDao.setTransactionManager(getTransactionManager());
//
//		User s = new User("student@ben.d2l.com", "Star", "Lord", 1, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		User a = new User("alumni@ben.d2l.com", "Star", "Lord", 2, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		User f = new User("faculty@ben.d2l.com", "Star", "Lord", 3, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		try {
//			userDao.addUser(s);
//			userDao.addUser(a);
//			userDao.addUser(f);
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("addUser() failed");
//
//		}
//
//	}

//	@org.junit.Test
//	@Transactional
//	@Rollback(true)
//	public void testGetObjectByID() {
//
//		userDao.setDataSource(getDataSource());
//		userDao.setTransactionManager(getTransactionManager());
//
//		User u = new User("example@ben.d2l.com", "Star", "Lord", 50, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		try {
//			userDao.addUser(u);
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("addUser() failed");
//
//		}
//
//		try {
//
//			if (userDao.getObjectById(1) == null) {
//				throw new AssertionFailedError("getObjectById() Failed");
//			}
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("getObjectById() Failed");
//		}
//	}

//	@org.junit.Test
//	@Transactional
//	@Rollback(true)
//	public void testGetters() {
//
//		userDao.setDataSource(getDataSource());
//		userDao.setTransactionManager(getTransactionManager());
//
//		User s = new User("student@ben.d2l.com", "Star", "Lord", 1, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		User a = new User("alumni@ben.d2l.com", "Star", "Lord", 2, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		User f = new User("faculty@ben.d2l.com", "Star", "Lord", 3, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		userDao.addUser(s);
//		userDao.addUser(a);
//		userDao.addUser(f);
//
//		try {
//			if (userDao.getAll() == null) {
//				throw new AssertionFailedError("getAll() failed");
//			}
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("getAll() failed");
//		}
//
//		try {
//			if (userDao.getAllStudents() == null) {
//				throw new AssertionFailedError("getAllStudents() failed");
//			}
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("getAllStudents() failed");
//		}
//
//		try {
//			if (userDao.getAllAlumni() == null) {
//				throw new AssertionFailedError("getAllAlumni() failed");
//			}
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("getAllAlumni() failed");
//		}
//
//		try {
//			if (userDao.getAllFaculty() == null) {
//				throw new AssertionFailedError("getAllFaculty() failed");
//			}
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("getAllFaculty() failed");
//		}
//
////		try {
////			if (userDao.getByEmail(s.getEmail()) == null) {
////				throw new AssertionFailedError("getByEmail() failed");
////			}
////
////		} catch (Exception e) {
////			throw new AssertionFailedError("getByEmail() failed");
////		}
////
////		try {
////			if (userDao.getByPersonalEmail(s.getPersonalEmail()) == null) {
////				throw new AssertionFailedError("getByPersonalEmail() failed");
////			}
////
////		} catch (Exception e) {
////			throw new AssertionFailedError("getByPersonalEmail() failed");
////		}
//	}
//
//	@org.junit.Test
//	@Transactional
//	@Rollback(true)
//	public void testUpdate() {
//
//		userDao.setDataSource(getDataSource());
//		userDao.setTransactionManager(getTransactionManager());
//
//		User u = new User("example@ben.d2l.com", "Star", "Lord", 50, 1234567, 1989, "Smuggler", "Captain", "IV",
//				"RocketCanSuckIt");
//
//		u.setPersonalEmail("old1@old.co");
//		userDao.addUser(u);
//		u.setPersonalEmail("new1@new.co");
//
//		try {
//			userDao.updateUser(u);
//			User n = userDao.getByPersonalEmail(u.getPersonalEmail());
//
//			if (n != null) {
//				throw new AssertionFailedError("updateUser() failed");
//			}
//
//		} catch (Exception e) {
//			throw new AssertionFailedError("updateUser() failed");
//		}
//	}

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
