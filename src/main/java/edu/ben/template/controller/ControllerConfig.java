package edu.ben.template.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.ben.template.dao.DaoConfig;

/**
 * DI config for controllers. Loaded from the <tt>WebApp</tt> class
 */
@Configuration
@Import({ DaoConfig.class })
public class ControllerConfig {

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}

	@Bean
	public LoginController loginController() {
		return new LoginController();
	}

	@Bean
	public UserController userController() {
		return new UserController();
	}
	
	@Bean
	public FileUploadController fileUploadController() {
		return new FileUploadController();
	}

}