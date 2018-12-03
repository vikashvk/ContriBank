package com.cb.dao;

import com.cb.bean.UserTable;

public interface LoginDao {
	/**
	 * @param username 
	 * @param password
	 * verifies the authentication details of user
	 */
	public boolean verifyCredentials(String username, String password);

	/**
	 * @param username
	 * @param password
	 * verify the lock status of the customer account
	 */
	public String checklockstatus(String username, String password);

	/**
	 * @param username
	 * display the user id whose account is locked
	 */
	public void lockAccount(String username);

	/**
	 * @param username
	 * verifies the secret answer whether it match with the earlier answer
	 */
	public String checkSecretAns(String username);

	/**
	 * @param username
	 * @param string
	 * to update password
	 */
	public void setpassword(String username, String string);

	/**
	 * @param id
	 * @param password
	 * Verifies the admin credentials
	 */
	public boolean adminLogin(int id, String password);

	/**
	 * @param ut
	 * @param username
	 * to update the user table
	 */
	public void updateUser(UserTable ut, String username);


}
