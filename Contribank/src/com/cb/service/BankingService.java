package com.cb.service;

import java.util.List;

import com.cb.bean.Payee;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public interface BankingService {
	
	public boolean validateUser(String userName, String password);
	public List<Integer> getUserAccounts(String userName);
	public List<Transaction> miniStatement(int accNo);
	public List<Transaction> detailedStatement(int accNo,String startDate,String endDate);
	public int getCurrentAcNo(String userName);
	public String getCurrentAddress(String userName);
	public String updateAddress(String userName);
	public String changePassword(String userName,String oldPass,String newPass);
	public String chequeRequest(int accNo);
	public List<ServiceTracker> getAllServiceRequested(int accNo);
	public List<ServiceTracker> getServiceRequestById(String userName, int requestID);
	public double getAcAvailableBalance(int fromAcChoice,int amt);
	public double fundTransfer(int toAcChoice,int fromAcChoice,int amt);
	public List<Payee> getPayeeAccountId(String userName);
	public String addPayee(String user_id,int payee_account_id,String nickname);
	public boolean isAccountExist(int account_no, String userName);
	public int getURN();
	public String validateAndCreatePayeeAccount(String userName);
	public void checkBalanceAndMakeTransaction(int toAcChoice,int fromAcChoice,int amt);
	public boolean checkTransactionPassword(String userName, String tnxPassword);
	public boolean isUserExist(String username);
	
}
