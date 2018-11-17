package com.cb.dao;

import java.util.List;

import com.cb.bean.Payee;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;

public interface BankingDao {

	public boolean validateUser(String userName, String password);
	public List<Integer> getUserAccounts(String userName);
	
	public List<Transaction> miniStatement(int accNo);
	public List<Transaction> detailedStatement(int accNo,String startDate,String endDate);
	
	public String getCurrentAddress(String userName);
	public String updateAddress(String userName);
	public String changePassword(String userName,String oldPass,String newPass);
	
	public String chequeRequest(int accNo);
	public List<ServiceTracker> getServiceRequestById(String userName, int requestID);
	
	public List<ServiceTracker> getAllServiceRequested(int accNo);
	
	public double getAcAvailableBalance(int fromAcChoice,int amt);
	public double fundTransfer(int toAcChoice,int fromAcChoice,int amt);

	public List<Payee> PayeeAccountId(String userName);
	
	public String addPayee(int user_id,int payee_account_id,String nickname);
	public boolean isAccountExist(int account_no, String userName);
	public boolean checkTransactionPassword(String userName, String tnxPassword);
	public boolean isUserExist(String username);
}
