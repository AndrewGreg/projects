package edu.ben.template.model;

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

//
//	public EmailGenerator() {
//		super();
//	}

	public static void generateAccountCreationEmail(String email, String pass) {

		
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");
		final String username = "benedictineAlumniTracker@gmail.com";//
		final String password = "TeamElg00g";
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
			//Personal Email for debugging
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("chitown042@gmail.com", false));
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
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
