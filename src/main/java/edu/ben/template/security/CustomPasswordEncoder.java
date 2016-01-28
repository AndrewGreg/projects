package edu.ben.template.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.codec.Utf8;

public class CustomPasswordEncoder extends ShaPasswordEncoder {
	// logging
	final Logger _log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	public CustomPasswordEncoder(int strength) {
		super(strength);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		String pass1 = "" + encPass;
		String pass2 = encodePassword(rawPass, salt);
		return equals(pass1, pass2);
	}

	public static String encodePassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			StringBuffer sb = new StringBuffer();
			/* TODO Need to write this... */

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Constant time comparison to prevent against timing attacks.
	 * 
	 * @param expected
	 * @param actual
	 * @return
	 */
	static boolean equals(String expected, String actual) {
		byte[] expectedBytes = bytesUtf8(expected);
		byte[] actualBytes = bytesUtf8(actual);
		int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
		int actualLength = actualBytes == null ? -1 : actualBytes.length;
		if (expectedLength != actualLength) {
			return false;
		}

		int result = 0;
		for (int i = 0; i < expectedLength; i++) {
			result |= expectedBytes[i] ^ actualBytes[i];
		}
		return result == 0;
	}

	private static byte[] bytesUtf8(String s) {
		if (s == null) {
			return null;
		}

		return Utf8.encode(s);
	}
}
