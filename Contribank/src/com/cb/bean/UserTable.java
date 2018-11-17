package com.cb.bean;

public class UserTable {
	private String user_id,login_password, secret_question,
			Transaction_password, lock_status, Pancard;

	@Override
	public String toString() {
		return "UserTable [user_id=" + user_id + ", login_password="
				+ login_password + ", secret_question=" + secret_question
				+ ", Transaction_password=" + Transaction_password
				+ ", lock_status=" + lock_status + ", Pancard=" + Pancard + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	public String getSecret_question() {
		return secret_question;
	}

	public void setSecret_question(String secret_question) {
		this.secret_question = secret_question;
	}

	public String getTransaction_password() {
		return Transaction_password;
	}

	public void setTransaction_password(String transaction_password) {
		Transaction_password = transaction_password;
	}

	public String getLock_status() {
		return lock_status;
	}

	public void setLock_status(String lock_status) {
		this.lock_status = lock_status;
	}

	public String getPancard() {
		return Pancard;
	}

	public void setPancard(String pancard) {
		Pancard = pancard;
	}
}
