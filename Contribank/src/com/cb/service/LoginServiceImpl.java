package com.cb.service;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.cb.bean.UserTable;
import com.cb.dao.LoginDao;
import com.cb.dao.LoginDaoImpl;
import com.cb.exception.BankingException;

public class LoginServiceImpl implements LoginService {

	LoginDao ldao = new LoginDaoImpl();
	Scanner sc = new Scanner(System.in);
	@Override
	public boolean adminLogin(int adminId, String adminPassword) {
		// TODO Auto-generated method stub
		return ldao.adminLogin(adminId, adminPassword);
	}

	@Override
	public boolean verifyCredentials(String username, String password) {
		// TODO Auto-generated method stub
		return ldao.verifyCredentials(username, password);
	}

	@Override
	public String checklockstatus(String username, String password) {
		// TODO Auto-generated method stub
		return ldao.checklockstatus(username, password);
	}

	@Override
	public void setpassword(String username, String new_pass) {
		// TODO Auto-generated method stub
		ldao.setpassword(username, new_pass);
	}

	@Override
	public void lockAccount(String username) {
		// TODO Auto-generated method stub
		ldao.lockAccount(username);
	}

	@Override
	public void updateUser(UserTable ut, String username) {
		// TODO Auto-generated method stub
		ldao.updateUser(ut, username);
	}

	@Override
	public String checkSecretAns(String username) {
		// TODO Auto-generated method stub
		return ldao.checkSecretAns(username);
	}

	@Override
	public boolean validateSecretAns(String ans) throws BankingException {
		// TODO Auto-generated method stub
		String ans_ = "[a-zA-z0-9*&!]{3,8}";
		if(Pattern.matches(ans_, ans)) 
			return true;
		else
			throw new BankingException("Answer can contain Numbers, Characters, &, * and !. Minimum 3 & Maximum 8 Characters.");
	}

	@Override
	public boolean validatePassword(String new_pass) throws BankingException {
		String pass = "[a-zA-Z0-9*&!]{8,15}";
		if(Pattern.matches(pass, new_pass)) 
			return true;
		else
			throw new BankingException("Password can contain Numbers, Characters, &, * and !. Minimum 8 & Maximum 15 Characters.");
	
	}

}
