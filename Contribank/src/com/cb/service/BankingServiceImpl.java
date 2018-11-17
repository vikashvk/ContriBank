package com.cb.service;

import java.util.List;

import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public class BankingServiceImpl implements BankingService {

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
	public boolean adminLogin(int id, String password) {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public boolean isUserExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountExist(int acno, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getURN() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String validateAndCreatePayeeAccount(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkBalanceAndMakeTransaction(int toAcChoice,
			int fromAcChoice, int amt) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkTransactionPassword(String userName, String tnxPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getCurrentAcNo(String userName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Transaction> miniStatement(int acChoice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> detailedStatement(int acChoice,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] getCurrentAddress(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char[] updateAddress(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
