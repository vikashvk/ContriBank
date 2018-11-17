package com.cb.dao;

import com.cb.bean.UserTable;

public class LoginDaoImpl implements LoginDao {

	@Override
	public boolean verifyCredentials(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String checklockstatus(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lockAccount(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String checkSecretAns(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setpassword(String username, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean adminLogin(int id, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateUser(UserTable ut, String username) {
		// TODO Auto-generated method stub
		
	}


}
