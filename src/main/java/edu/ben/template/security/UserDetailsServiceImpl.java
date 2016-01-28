package edu.ben.template.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ben.template.dao.UserDao;

public class UserDetailsServiceImpl implements UserDetailsService {
	// logging
	final Logger _log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	// data
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/* TODO Need to write this... */
		return null;
	}

	public UserDao getUserDao() {
		return userDao;
	}
}
