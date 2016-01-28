package edu.ben.template.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * this is here to just hold all the dao objects
 */
@Import({ DaoConfig.class })
public class DaoKeeper {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserDao userDao;

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
