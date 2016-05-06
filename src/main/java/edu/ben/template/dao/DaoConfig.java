package edu.ben.template.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import edu.ben.template.model.EmailGenerator;


/**
 * DI config for controllers. Loaded from the <tt>WebApp</tt> class. This is a
 * base class that contains all the generic daos, with the datasource configs in
 * the extending classes
 */
@Configuration
public class DaoConfig {

	@Bean
	public DataSource dataSource() throws IOException, ClassNotFoundException {
		// create the data source (use the built in tomcat one)
		BasicDataSource dataSource = new BasicDataSource();
		// if (EnvUtil.isProduction()) {
		// dataSource.setDriverClassName("com.mysql.jdbc.GoogleDriver");
		// dataSource.setUrl("jdbc:google:mysql://database");
		// } else {
		
		Class.forName("com.mysql.jdbc.Driver");
		
//		Properties props = new Properties();
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		props.load(classLoader.getResourceAsStream("config.properties"));
//		String MYSQL_PREFIX = props.getProperty("MYSQL_PREFIX");
//		String HOST = props.getProperty("HOST");
//		String PORT = props.getProperty("PORT");
//		String DB_NAME = props.getProperty("DB_NAME");
//		String USERNAME = props.getProperty("USERNAME");
//		String PASSWORD = props.getProperty("PASSWORD");
	
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl(MYSQL_PREFIX + HOST + ":" + PORT + "/" + DB_NAME);
		// }
		// org.apache.tomcat.jdbc.pool.DataSource dataSource = new
		// org.apache.tomcat.jdbc.pool.DataSource();
		// dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/alumnitracker");
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
		try {
			transactionManager.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public TestimonialDao testimonialDao() throws IOException {
		// create the dao
		TestimonialDao dao = new TestimonialDao();
		// set the specifics
		// dao.setCache(cacheFactory());
		try {
			dao.setDataSource(dataSource());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.setTransactionManager(transactionManager());
		// return it
		return dao;
	}

	@Bean
	public EmailGenerator emailGenerator() {
		// create new object mapper
		//Properties props = System.getProperties();


//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		try {
//			props.load(classLoader.getResourceAsStream("config.properties"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}

//		String host = props.getProperty("EMAIL_HOST");
//		String port = props.getProperty("EMAIL_PORT");
//		String debug = props.getProperty("EMAIL_DEBUG");
//		final String username = props.getProperty("EMAIL_ADDRESS");
//		final String password = props.getProperty("EMAIL_PASSWORD");
//		
		
		
		String host = "smtp.gmail.com";
		String port = "465";
		String debug = "true";
		final String username = "benedictineAlumniTracker@gmail.com";
		final String password = "TeamElg00g";
		
		EmailGenerator generator = new EmailGenerator();
		generator.setHost(host);
		generator.setPort(port);
		generator.setDebug(debug);
		generator.setUsername(username);
		generator.setPassword(password);
		
		return generator;
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