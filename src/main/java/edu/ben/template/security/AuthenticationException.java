package edu.ben.template.security;

import java.io.Serializable;

/**
 * 
 * AuthenticationException
 **/
public class AuthenticationException extends Exception implements Serializable {
	// serialize
	private static final long serialVersionUID = -2234614369350025230L;

	public AuthenticationException(String message) {
		super(message);
	}
}
