/**
 * 
 */
package com.cb.service;

import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public interface LoginService {
	public boolean verifyCredentials(String username, String password);

	public String checklockstatus(String username, String password);

	public void setpassword(String username, String new_pass);

	public void lockAccount(String username);

	public boolean adminLogin(int id, String password);

	public void updateUser(UserTable ut, String username);

	public String checkSecretAns(String username);

	public boolean validateSecretAns(String ans) throws BankingException;

	public boolean validatePassword(String new_pass) throws BankingException;

}
