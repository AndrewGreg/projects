package edu.ben.template.dao;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

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
		dataSource.setUsername("alumniTracker");
		dataSource.setPassword("alumnitracker");
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

	// @Autowired
	// @Bean
	// public FileUploadDao getUserDao(SessionFactory sessionFactory) {
	// return new FileUploadDaoImpl(sessionFactory);
	// }
	//
	// @Bean
	// public SessionFactory sessionFactory(){
	// AnnotationSessionFactoryBean sessionFactoryBean = new
	// AnnotationSessionFactoryBean();
	// sessionFactoryBean.setConfigLocation(new
	// ClassPathResource("hibernate.cfg.xml"));
	// sessionFactoryBean.afterPropertiesSet();
	// return sessionFactoryBean.getObject();
	// }

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(20971520); // 20MB
		multipartResolver.setMaxInMemorySize(1048576); // 1MB
		return multipartResolver;
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
	public DepartmentDao departmentDao() throws IOException {
		// create the dao
		DepartmentDao dao = new DepartmentDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public CollegeDao collegeDao() throws IOException {
		// create the dao
		CollegeDao dao = new CollegeDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public ContactDao contactDao() throws IOException {
		// create the dao
		ContactDao dao = new ContactDao();
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
	public JobDao jobDao() throws IOException {
		// create the dao
		JobDao dao = new JobDao();
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
	public TitleDao titleDao() throws IOException {
		// create the dao
		TitleDao dao = new TitleDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public TransferDao transferDao() throws IOException {
		// create the dao
		TransferDao dao = new TransferDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public ReasonDao reasonDao() throws IOException {
		// create the dao
		ReasonDao dao = new ReasonDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public FileUploadDao FileUploadDao() throws IOException {
		// create the dao
		FileUploadDao dao = new FileUploadDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		dao.setDataSource(dataSource());
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public ImageUploadDao ImageUploadDao() throws IOException {
		// create the dao
		ImageUploadDao dao = new ImageUploadDao();
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