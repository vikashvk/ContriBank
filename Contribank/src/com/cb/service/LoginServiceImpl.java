package com.cb.service;

import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public class LoginServiceImpl implements LoginService {

	@Override
	public boolean adminLogin(int adminId, String adminPassword) {
		// TODO Auto-generated method stub
		return false;
	}

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
	public void setpassword(String username, String new_pass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockAccount(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserTable ut, String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String checkSecretAns(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateSecretAns(String ans) throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatePassword(String new_pass) throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}

}
