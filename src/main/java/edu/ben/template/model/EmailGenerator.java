package edu.ben.template.model;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailGenerator {

	private static String username;
	private static String password;
	private static String host;
	private static String port;
	private static String debug;

	public EmailGenerator() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		EmailGenerator.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		EmailGenerator.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		EmailGenerator.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		EmailGenerator.port = port;
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		EmailGenerator.debug = debug;
	}

	public static void generateAccountCreationEmail(String email, String pass) {

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try {
			props.load(classLoader.getResourceAsStream("config.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Used to set properties
		String h = host;
		String p = port;
		String d = debug;

		props.setProperty("mail.smtp.host", h);
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", p);
		props.setProperty("mail.smtp.socketFactory.port", p);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", d);
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(username));
			// Site Email for debugging
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username, false));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
			msg.setSubject("Benedictine University's Alumnitracker Account Verification");
			msg.setText(
					"Thank you for registerring an account with Benedictine University's Alumnitracker. To complete your first login please use the temporary password provided in this email.\n\nUsername:\t\t"
							+ email + "\nTemporary Password:\t" + pass
							+ "\n\nPlease remember to change your password after login!");

			msg.setSentDate(new Date());
			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
