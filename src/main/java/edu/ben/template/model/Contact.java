package edu.ben.template.model;

/**
 * Class that uses the information of the user class.
 * 
 * @author Donald Kirk
 * @version 1.0
 *
 */
public class Contact extends User {

	/**
	 * Creates a serial ID.
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String phoneNumber;
	private String message;
	private int userId;
	private int recipientId;

	/**
	 * Empty constructor
	 */
	public Contact() {
		super();
	}

	/**
	 * Constructor that passes the name, email,phone number, and message.
	 * 
	 * @param name
	 * @param email
	 * @param phoneNumber
	 * @param message
	 */
	public Contact(String name, String email, String phoneNumber, String message, int userId, int recipientId) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.message = message;
		this.userId = userId;
		this.recipientId = recipientId;
	}

	/**
	 * 
	 * @return the name of the contact.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the contact.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email from the contact.
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of the contact.
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return the phone number of the contact.
	 */
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phone number of the contact.
	 * 
	 * @param phoneNumber
	 */
	@Override
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return the message that was made by the contact.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set the message for the contact.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return the user Id associated with the contact
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Set the user Id.
	 * 
	 * @param userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return the recipient Id.
	 */
	public int getRecipientId() {
		return recipientId;
	}

	/**
	 * Set the recipient Id.
	 * 
	 * @param recipientId
	 */
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	/**
	 * Displays the contact information in String form.
	 */
	@Override
	public String toString() {
		return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", message=" + message
				+ ", userId=" + userId + ", recipientId=" + recipientId + "]";
	}

}
