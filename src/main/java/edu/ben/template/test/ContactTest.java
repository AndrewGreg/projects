package edu.ben.template.test;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import edu.ben.template.dao.ContactDao;
import edu.ben.template.model.Contact;
import edu.ben.template.model.User;
import junit.framework.AssertionFailedError;

public class ContactTest {

	@Autowired
	private ContactDao contactDao = new ContactDao();

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

		contactDao.setDataSource(getDataSource());
		contactDao.setTransactionManager(getTransactionManager());

		User user = new User();
		user.setId((long) 1);
		User user2 = new User();
		user2.setId((long) 2);
		User user3 = new User();
		user3.setId((long) 3);

		Contact contact = new Contact("Donald Kirk", "b2160104@ben.edu", "(871)725-0000", "This is a message", 1, 2);
		Contact contact2 = new Contact("Chris Detloff", "b2177104@ben.edu", "(871)725-0000", "This is a message 3", 2,
				3);
		Contact contact3 = new Contact("Jose Dieck", "b1111104@ben.edu", "(871)725-0000", "This is a message 2", 3, 1);

		try {
			contactDao.addContact(contact);
			contactDao.addContact(contact2);
			contactDao.addContact(contact3);

		} catch (Exception e) {
			throw new AssertionFailedError("addContact() failed");

		}

	}

	// @Test
	// @Transactional
	// @Rollback(true)
	// public void testGetters() {
	//
	// contactDao.setDataSource(getDataSource());
	// contactDao.setTransactionManager(getTransactionManager());
	//
	// Contact contact = new Contact("Donald Kirk", "b2160104@ben.edu",
	// 87172500, "This is a message", 1, 2);
	// Contact contact2 = new Contact("Chris Detloff", "b2177104@ben.edu",
	// 125676, "This is a message 3", 2, 3);
	// Contact contact3 = new Contact("Jose Dieck", "b1111104@ben.edu", 1234567,
	// "This is a message 2", 3, 1);
	//
	// contactDao.addContact(contact);
	// contactDao.addContact(contact2);
	// contactDao.addContact(contact3);
	//
	// try {
	//
	// if (contactDao.getAll() != null) {
	// System.out.println("All print out!");;
	// }
	//
	// } catch (Exception e) {
	// throw new AssertionFailedError("getAll() Failed");
	// }
	//
	// }

	@Test
	@Transactional
	@Rollback(true)
	public void testUpdateEvent() {

		contactDao.setDataSource(getDataSource());
		contactDao.setTransactionManager(getTransactionManager());

		Contact contact = new Contact("Donald Kirk", "b2160104@ben.edu", "(871)725-0000", "This is a message", 1, 2);
		Contact contact2 = new Contact("Chris Detloff", "b2177104@ben.edu", "(871)725-0000", "This is a message 3", 2,
				3);
		Contact contact3 = new Contact("Jose Dieck", "b1111104@ben.edu", "(871)725-0000", "This is a message 2", 3, 1);

		try {
			contactDao.addContact(contact);
			contactDao.addContact(contact2);
			contactDao.addContact(contact3);

		} catch (Exception e) {
			throw new AssertionFailedError("addContact() failed");

		}

		contact.setName("Kirk donald");
		contact2.setName("Detloff Chris");
		contact3.setName("Dieck Jose");

		try {

			contactDao.updateContact(contact);
			contactDao.updateContact(contact2);
			contactDao.updateContact(contact3);

		} catch (Exception e) {
			throw new AssertionFailedError("updateContact() Failed");
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
