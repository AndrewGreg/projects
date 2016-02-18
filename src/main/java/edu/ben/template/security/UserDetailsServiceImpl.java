package edu.ben.template.security;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.ben.template.dao.UserDao;
import edu.ben.template.model.User;

public class UserDetailsServiceImpl implements UserDetailsService {
	// logging
	final Logger _log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	// data
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User person = getUserDao().findByEmail(username);
		if(person == null){
			throw new UsernameNotFoundException("User not found");	
		}
		System.out.println("im here");
		org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(person.getUsername(),person.getPassword(),Collections.singleton(new SimpleGrantedAuthority(Integer.toString(person.getRole()))));
		return user;
	}

	public UserDao getUserDao() {
		return userDao;
	}
}
