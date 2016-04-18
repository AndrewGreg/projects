package edu.ben.template.test;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.DepartmentDao;
import edu.ben.template.model.Department;
import junit.framework.AssertionFailedError;

public class DepartmentTest {

	@Autowired
	private DepartmentDao departmentDao = new DepartmentDao();

	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(true)
	public void test() {
		Assert.assertEquals(null, null);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testAddDepartment() {

		departmentDao.setDataSource(getDataSource());
		departmentDao.setTransactionManager(getTransactionManager());

		Department department = new Department((long) 1, "Benedictine University");

		try {
			departmentDao.addDepartment(department);

		} catch (Exception e) {
			throw new AssertionFailedError("addDepartment() failed");

		}

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetters() {

		departmentDao.setDataSource(getDataSource());
		departmentDao.setTransactionManager(getTransactionManager());

		Department department = new Department((long) 1, "Benedictine University");

		departmentDao.addDepartment(department);

		try {

			if (departmentDao.getAll() == null) {
				throw new AssertionFailedError("getAll() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getAll() Failed");
		}

		try {

			if (departmentDao.getObjectById(1) == null) {
				throw new AssertionFailedError("getByObject() Failed");
			}

		} catch (Exception e) {
			throw new AssertionFailedError("getByObject() Failed");
		}
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateEvent() {

		departmentDao.setDataSource(getDataSource());
		departmentDao.setTransactionManager(getTransactionManager());

		Department department = new Department((long) 1, "Benedictine University");
		Department department2 = new Department((long) 2, "North Central College");
		Department department3 = new Department((long) 3, "Ohio State University");

		try {
			departmentDao.addDepartment(department);
			departmentDao.addDepartment(department2);
			departmentDao.addDepartment(department3);

		} catch (Exception e) {
			throw new AssertionFailedError("addDepartment() failed");

		}

		department.setName("BenU University");
		department2.setName("South Central College");
		department3.setName("Ohio College");

		try {

			departmentDao.updateDepartment(department);
			departmentDao.updateDepartment(department2);
			departmentDao.updateDepartment(department3);

		} catch (Exception e) {
			throw new AssertionFailedError("updateDepartment() Failed");
		}

	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetObjectByID() {

		departmentDao.setDataSource(getDataSource());
		departmentDao.setTransactionManager(getTransactionManager());

		Department department = new Department((long) 1, "Benedictine University");
		Department department2 = new Department((long) 2, "North Central College");
		Department department3 = new Department((long) 3, "Ohio State University");

		try {
			departmentDao.addDepartment(department);
			departmentDao.addDepartment(department2);
			departmentDao.addDepartment(department3);

		} catch (Exception e) {
			throw new AssertionFailedError("addDepartment() failed");

		}

		try {

			if (departmentDao.getObjectById(1) == null) {
				throw new AssertionFailedError("getObjectById() Failed");
			}
			if (departmentDao.getObjectById(2) == null) {
				throw new AssertionFailedError("getObjectById() Failed");
			}
			if (departmentDao.getObjectById(3) == null) {
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
