package edu.ben.template.model;

public class Transfer {

	private int id;
	private String description;
	private boolean verified;
	private int reasonId;
	private int userId;
	private int verifiedId;
	private boolean hidden;

	public Transfer() {
		super();
	}

	public Transfer(int id, String description, boolean verified, int reasonId, int userId, int verifiedId,
			boolean hidden) {
		super();
		this.id = id;
		this.description = description;
		this.verified = verified;
		this.reasonId = reasonId;
		this.userId = userId;
		this.verifiedId = verifiedId;
		this.hidden = hidden;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getVerifiedId() {
		return verifiedId;
	}

	public void setVerifiedId(int verifiedId) {
		this.verifiedId = verifiedId;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

}
