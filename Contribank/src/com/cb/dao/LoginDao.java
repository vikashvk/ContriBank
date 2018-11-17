package com.cb.dao;

import com.cb.bean.UserTable;

public interface LoginDao {
	public boolean verifyCredentials(String username, String password);

	public String checklockstatus(String username, String password);

	public void lockAccount(String username);

	public String checkSecretAns(String username);

	public void setpassword(String username, String string);

	public boolean adminLogin(int id, String password);

	public void updateUser(UserTable ut, String username);


}
