package edu.ben.dao.test.junit;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.MajorDao;
import edu.ben.template.model.Major;
import edu.ben.template.model.User;
import junit.framework.AssertionFailedError;

public class MajorDaoTest {

	@Autowired
	private MajorDao majorDao = new MajorDao();

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void test() {
		Assert.assertEquals(null, null);
	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testInsertRecords() {

		majorDao.setDataSource(getDataSource());
		majorDao.setTransactionManager(getTransactionManager());

		Major major = new Major("test", false);
		Major concentration = new Major("test", true);
		concentration.setParent(major);
		User user = new User();
		user.setId((long) 1);

		try {
			majorDao.addMajor(major);

		} catch (Exception e) {
			throw new AssertionFailedError("addMajor() failed");

		}

		try {
			majorDao.addConcentration(concentration);

		} catch (Exception e) {
			throw new AssertionFailedError("addConcentration() failed");

		}

		try {
			majorDao.addMajorToUser(major, user);

		} catch (Exception e) {
			throw new AssertionFailedError("addMajorToUser() failed");

		}

		try {
			majorDao.addMinorToUser(major, user);

		} catch (Exception e) {
			throw new AssertionFailedError("addMinorToUser() failed");

		}

		try {
			majorDao.addConcentrationToUser(concentration, user);

		} catch (Exception e) {
			throw new AssertionFailedError("addConcentrationToUser() failed");

		}

	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testGetObjectByID() {

		majorDao.setDataSource(getDataSource());
		majorDao.setTransactionManager(getTransactionManager());

		Major major = new Major("test", false);

		try {
			majorDao.addMajor(major);

		} catch (Exception e) {
			throw new AssertionFailedError("addMajor() failed");

		}

		try {

			if (majorDao.getObjectById(1) == null) {
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

		majorDao.setDataSource(getDataSource());
		majorDao.setTransactionManager(getTransactionManager());

		Major major = new Major("new", false);
		major.setId((long) 23);
		Major concentration = new Major("testing", true);
		concentration.setId((long) 54);
		concentration.setParent(major);
		User user = new User();
		user.setId((long) 1);

		majorDao.addMajor(major);
		majorDao.addConcentration(concentration);
		majorDao.addMajorToUser(major, user);
		majorDao.addMinorToUser(major, user);
		majorDao.addConcentrationToUser(concentration, user);

		try {
			if (majorDao.getAll() == null) {
				throw new AssertionFailedError("getAll() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAll() failed");

		}

		try {
			if (majorDao.getByName("new") == null) {
				throw new AssertionFailedError("getByName() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByName() failed");

		}

		try {
			if (majorDao.getAllMajors() == null) {
				throw new AssertionFailedError("getAllMajors() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAllMajors() failed");

		}

		try {
			if (majorDao.getAllConcentrations() == null) {
				throw new AssertionFailedError("getAllConcentrations() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAllConcentrations() failed");
		}

		try {
			if (majorDao.getConcentrationByMajor(major) == null) {
				throw new AssertionFailedError("getConcentrationByMajor() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getConcentrationByMajor() failed");
		}

		try {
			if (majorDao.getMajorByUser(user) == null) {
				throw new AssertionFailedError("getMajorByUser() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getMajorByUser() failed");
		}

		try {
			if (majorDao.getMinorByUser(user) == null) {
				throw new AssertionFailedError("getMinorByUser() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getMinorByUser() failed");
		}

		try {
			if (majorDao.getConcentrationByUser(user) == null) {
				throw new AssertionFailedError("getConcentrationByUser() returns null");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getConcentrationByUser() failed");
		}

	}

	@org.junit.Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {

		majorDao.setDataSource(getDataSource());
		majorDao.setTransactionManager(getTransactionManager());

		Major major = new Major("new", false);
		major.setId((long) 23);
		Major concentration = new Major("testing", true);
		concentration.setId((long) 54);
		concentration.setParent(major);
		User user = new User();
		user.setId((long) 1);

		User temp = user;
		temp.addConcentration(concentration);
		temp.addMinor(major);
		temp.addMajor(major);

		try {
			majorDao.updateMajorAndConcentrationByUser(temp);
			if (majorDao.getConcentrationByUser(temp).equals(user.getConcentration())
					|| majorDao.getMajorByUser(temp).equals(user.getMajor())) {
				throw new AssertionFailedError("updateMajorAndConcentrationByUser() failed");
			}
		} catch (Exception e){
			throw new AssertionFailedError("updateMajorAndConcentrationByUser() failed");
		}
		
		try {
			majorDao.updateMinorByUser(temp);
			if (majorDao.getMinorByUser(temp).equals(user.getMinor())) {
				throw new AssertionFailedError("updateMinorByUser() failed");
			}
		} catch (Exception e){
			throw new AssertionFailedError("updateMinorByUser() failed");
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
