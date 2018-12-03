/**
 * 
 */
package com.cb.service;

import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public interface LoginService {
	/**
	 * @param username
	 * @param password
	 * method to verify the authentication details of user
	 */
	public boolean verifyCredentials(String username, String password);

	/**
	 * @param username
	 * @param password
	 * method to checks the lock status of the user account 
	 */
	public String checklockstatus(String username, String password);

	/**
	 * @param username
	 * @param new_pass
	 * method to set the password of user
	 */
	public void setpassword(String username, String new_pass);

	/**
	 * @param username
	 * method to get the id of locked account
	 */
	public void lockAccount(String username);

	/**
	 * @param id
	 * @param password
	 * method to verify the admin login credentials 
	 */
	public boolean adminLogin(int id, String password);

	/**
	 * @param ut
	 * @param username
	 * method to make updates in user table
	 */
	public void updateUser(UserTable ut, String username);

	/**
	 * @param username
	 * method to check secret answers in database
	 */
	public String checkSecretAns(String username);

	/**
	 * @param ans
	 * validate the secret ans which has alphabets,numbers,special characters and in specified length
	 * @throws BankingException --will display exception message
	 */
	public boolean validateSecretAns(String ans) throws BankingException;

	/**
	 * @param new_pass
	 * validate the password which has alphanumeric along with special characters and in specified length
	 * @throws BankingException --will display exception message
	 */
	public boolean validatePassword(String new_pass) throws BankingException;

}
