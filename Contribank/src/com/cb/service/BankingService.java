package com.cb.service;

import java.util.List;

import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public interface BankingService {
	public boolean verifyCredentials(String username, String password);

	public String checklockstatus(String username, String password);

	public void setpassword(String username, String new_pass);

	public void lockAccount(String username);

	public boolean adminLogin(int id, String password);

	public void updateUser(UserTable ut, String username);

	public String checkSecretAns(String username);

	public boolean validateSecretAns(String ans) throws BankingException;

	public boolean validatePassword(String new_pass) throws BankingException;

	public boolean isUserExist(String username);

	public boolean isAccountExist(int acno, int i);

	public int getURN();

	public String validateAndCreatePayeeAccount(String userName);

	public void checkBalanceAndMakeTransaction(int toAcChoice,
			int fromAcChoice, int amt);

	public boolean checkTransactionPassword(String userName, String tnxPassword);

	public List<Transaction> detailedStatement(int acno, String startDate,
			String endDate);

	public int getCurrentAcNo(String userName);

	public List<Transaction> miniStatement(int acChoice);

	public char[] getCurrentAddress(String userName);

	public char[] updateAddress(String userName);

}
