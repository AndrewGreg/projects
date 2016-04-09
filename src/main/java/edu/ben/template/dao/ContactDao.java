package edu.ben.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import edu.ben.template.model.Contact;

public class ContactDao extends BaseDao<Contact> {

	static final String SEARCH = "SELECT * FROM ";

	/**
	 * Retrieves all Contacts from database.
	 * 
	 * @return all contacts.
	 */
	public ArrayList<Contact> getAll() {

		List<Contact> contacts = new ArrayList<Contact>();
		String sql = SEARCH + "contact ORDER BY name";

		try {
			contacts = jdbcTemplate.query(sql, getRowMapper());
			return (ArrayList<Contact>) contacts;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	/**
	 * Inserts the Contact into the database.
	 * 
	 * @param contact
	 *            entails of name, email, phone number, and the message.
	 */
	public void addContact(Contact contact) {

		String sql = "INSERT INTO contact (name, email, phone_number, message,user_id,recipient_id) VALUES (?, ?, ?, ?,?,?)";

		jdbcTemplate.update(sql, new Object[] { contact.getName(), contact.getEmail(), contact.getPhoneNumber(),
				contact.getMessage(), contact.getUserId(), contact.getRecipientId() });

		return;
	}

	/**
	 * Enables the user to edit the following attributes.
	 * 
	 * @param contact
	 */
	public void updateContact(Contact contact) {

		String sql = "UPDATE contact SET name = ?, email = ?, phone_number = ?, message = ?,user_id = ?, recipient_id = ?  WHERE user_id = ?";
		try {
			jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getPhoneNumber(),
					contact.getMessage(), contact.getUserId(), contact.getRecipientId(), contact.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;

	}

	/**
	 * Row Mapper.
	 */
	@Override
	public RowMapper<Contact> getRowMapper() {
		return new RowMapper<Contact>() {
			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object
				Contact contact = new Contact();
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setPhoneNumber(rs.getInt("phoneNumber"));
				contact.setMessage(rs.getString("message"));
				contact.setUserId(rs.getInt("userId"));
				contact.setRecipientId(rs.getInt("recipientId"));

				return contact;
			}
		};
	}

	/**
	 * Creates the connection to the database.
	 */
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Contact contact) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into contact (id, name) values (?,?) " + "on duplicate key update name = values(name)",
						new String[] { "id" });
				ps.setLong(1, contact.getId());
				ps.setString(2, contact.getName());
				return ps;
			}
		};
	}

}
