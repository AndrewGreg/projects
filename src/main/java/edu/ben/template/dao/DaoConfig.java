package edu.ben.template.dao;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

/**
 * DI config for controllers. Loaded from the <tt>WebApp</tt> class. This is a
 * base class that contains all the generic daos, with the datasource configs in
 * the extending classes
 */
@Configuration
public class DaoConfig {

	@Bean
	public DataSource dataSource() {
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
		dataSource.setPassword("");
		dataSource.setValidationQuery("select id from user limit 1");
		// dataSource.setValidationInterval(30000);
		// dataSource.setLogValidationErrors(true);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setRemoveAbandoned(true);
		dataSource.setLogAbandoned(true);
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}

	@Bean
	public UserDao userDao() throws IOException {
		// create the dao
		UserDao dao = new UserDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}
	
	@Bean
	public EventDao eventDao() throws IOException {
		// create the dao
		EventDao dao = new EventDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public JobPostingDao jobPostingDao() throws IOException {
		// create the dao
		JobPostingDao dao = new JobPostingDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}
	
	@Bean
	public MajorDao majorDao() throws IOException {
		// create the dao
		MajorDao dao = new MajorDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public InterestDao interestDao() throws IOException {
		// create the dao
		InterestDao dao = new InterestDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public ObjectMapper objectMapper() {
		// create new object mapper
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new AfterburnerModule());
		mapper.registerModule(new JodaModule());
		return mapper;
	}

}