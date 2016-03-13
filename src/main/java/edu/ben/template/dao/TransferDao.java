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

import edu.ben.template.model.Reason;
import edu.ben.template.model.Transfer;
import edu.ben.template.model.User;

public class TransferDao extends BaseDao<Transfer> {

	public TransferDao() {
		super();
	}

	public Transfer getObjectById(long transferId) {
		return this.getObjectById(transferId, false);
	}

	public Transfer getObjectById(long transferId, boolean complete) {
		if (transferId == 0) {
			/* Probably want to log this */
			return null;
		}
		Transfer object = null;
		// check if we found the object
		if (object == null) {
			try {
				// look up the object
				String sql = "SELECT * FROM transfer WHERE id = ?";
				object = this.jdbcTemplate.queryForObject(sql, new Object[] { transferId }, getRowMapper());
			} catch (EmptyResultDataAccessException e) {
				/* Probably want to log this */
				return null;
			}
		}
		return object;
	}

	// TODO
	public void addTransfer(Transfer transfer, User user) {

		// Created, lastModified, lastLogin, lastActive will be implemented in
		// future sprint
		String sql = "INSERT INTO transfer (description, verified, reason_id, user_id, verified_id, hidden) VALUES (?,?,?,?,?,?);";

		try {
			jdbcTemplate.update(sql, new Object[] { transfer.getDescription(), transfer.isVerified(),
					transfer.getReasonId(), user.getId(), transfer.getVerifiedId(), transfer.isHidden() });

		} catch (Exception e) {
			/* Probably want to log this */
		}
	}

	public ArrayList<Transfer> getAll() {

		List<Transfer> transfers = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfer";

		try {
			transfers = jdbcTemplate.query(sql, getRowMapper());

			return (ArrayList<Transfer>) transfers;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Transfer> getByUser(User user) {

		List<Transfer> transfers = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfer WHERE user_id = ?";

		try {
			transfers = jdbcTemplate.query(sql, new Object[] { user.getId() }, getRowMapper());

			return (ArrayList<Transfer>) transfers;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}
	}

	public ArrayList<Transfer> getByReason(Reason reason) {

		List<Transfer> transfers = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfer WHERE reason_id = ?";

		try {
			transfers = jdbcTemplate.query(sql, new Object[] { reason.getId() }, getRowMapper());

			return (ArrayList<Transfer>) transfers;
		} catch (EmptyResultDataAccessException e) {
			/* Probably want to log this */
			return null;
		}

	}

	public void updateTransfer(Transfer transfer) {

		String sql = "UPDATE transfer SET `description`=?, `verified`=?, `reason_id`=?, `user_id`=?, `verified_id`=?, `hidden`=? WHERE `id`=?;";

		try {
			jdbcTemplate
					.update(sql,
							new Object[] { transfer.getDescription(), transfer.isVerified(), transfer.getReasonId(),
									transfer.getUserId(), transfer.getVerifiedId(), transfer.isHidden(),
									transfer.getId() });
		} catch (Exception e) {
			/* Probably want to log this */
		}
		return;

	}

	@Override
	public RowMapper<Transfer> getRowMapper() {
		return new RowMapper<Transfer>() {
			public Transfer mapRow(ResultSet rs, int rowNum) throws SQLException {
				// map result set to object

				Transfer t = new Transfer();
				t.setId(rs.getInt("id"));
				t.setDescription(rs.getString("description"));
				t.setVerified(rs.getBoolean("verified"));
				t.setReasonId(rs.getInt("reason_id"));
				t.setUserId(rs.getInt("user_id"));
				t.setVerifiedId(rs.getInt("verified_id"));
				t.setHidden(rs.getBoolean("hidden"));

				return t;
			}
		};
	}

	// TODO Check this
	@Override
	public PreparedStatementCreator getSavePreparedStatementCreator(final Transfer transfer) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into user (id, description, verified, reason_id, user_id, verified_id, hidden) values (?,?,?,?,?,?,?) "
								+ "on duplicate key update id = values(id)",
						new String[] { "id" });
				ps.setInt(1, transfer.getId());
				ps.setString(2, transfer.getDescription());
				return ps;
			}
		};
	}

}
