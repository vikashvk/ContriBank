package com.cb.service;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cb.bean.Payee;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.dao.BankingDao;
import com.cb.dao.BankingDaoImpl;
import com.cb.exception.BankingException;

public class BankingServiceImpl implements BankingService {

	BankingDao bDao = new BankingDaoImpl();
	Scanner sc = new Scanner(System.in);
	Logger serviceLogger = null;
	
	@Override
	public boolean validateUser(String userName, String password) {
		return bDao.validateUser(userName,password);
	}

	@Override
	public List<Integer> getUserAccounts(String userName) {
		return bDao.getUserAccounts(userName);
	}

	@Override
	public List<Transaction> miniStatement(int accNo) {
		return bDao.miniStatement(accNo);	
	}

	@Override
	public List<Transaction> detailedStatement(int accNo, String startDate,
			String endDate) {
		return bDao.detailedStatement(accNo, startDate, endDate);
	}

	@Override
	public int getCurrentAcNo(String userName) {
		//fetching all the accounts of current user -- here its 1001
		List<Integer> accList = getUserAccounts(userName);
		for(Integer a:accList)
			System.out.println(a);
		
		//storing the ac no. for which the user requested for operations
		System.out.println("Enter Account Number you want to proceed with");
		int acChoice=sc.nextInt();
		while(!accList.contains(acChoice))
		{
			System.out.println("Sorry You entered wrong Account Number\nEnter Valid Account Number:");
			acChoice=sc.nextInt();
		}
		return acChoice;
	}

	@Override
	public String getCurrentAddress(String userName) {
		return bDao.getCurrentAddress(userName);
	}

	@Override
	public String updateAddress(String userName) {
		return bDao.updateAddress(userName);
	}

	@Override
	public String changePassword(String userName, String oldPass, String newPass) {
		return bDao.changePassword(userName,oldPass,newPass);
	}

	@Override
	public String chequeRequest(int accNo) {
		return bDao.chequeRequest(accNo);	
	}

	@Override
	public List<ServiceTracker> getAllServiceRequested(int accNo) {
		return bDao.getAllServiceRequested(accNo);
	}

	@Override
	public List<ServiceTracker> getServiceRequestById(String userName,
			int requestID) {
		return bDao.getServiceRequestById(userName, requestID);
	}

	@Override
	public double getAcAvailableBalance(int fromAcChoice, int amt) {

		return bDao.getAcAvailableBalance(fromAcChoice, amt);
	}


	public double fundTransfer(int toAcChoice,int fromAcChoice,int amt)
	{
		return bDao.fundTransfer(toAcChoice,fromAcChoice, amt);
	}
	public List<Payee> getPayeeAccountId(String userName)
	{
		return bDao.PayeeAccountId(userName);
	}
	public String addPayee(String user_id,int payee_account_id,String nickname)
	{
		return bDao.addPayee(user_id,payee_account_id,nickname);
	}
	public boolean isAccountExist(int account_no, String userName)
	{
		return bDao.isAccountExist(account_no,userName);
	}
	public int getURN()
	{
		return (int)(Math.random()*10000);
	}
	public String validateAndCreatePayeeAccount(String userName)
	{
		System.out.println("Enter Payee Account Id");
		int toAc;
		do
		{
			toAc=sc.nextInt();
			if(!isAccountExist(toAc,userName))
				System.out.println("No such account exist. Enter valid Ac no");
		}while(!isAccountExist(toAc,userName));
		int sysURN, userURN;
		do
		{
			sysURN=getURN();
			System.out.println("URN: "+sysURN+"\nEnter Displayed URN:");
			userURN=sc.nextInt();
			if(sysURN!=userURN)
				System.out.println("Invalid URN! Try again...");
		}while(sysURN!=userURN);
		System.out.println("Enter payee nickname");
		return "Payee added: "+addPayee(userName,toAc,sc.next());
	}
	
	public void checkBalanceAndMakeTransaction(int toAcChoice,int fromAcChoice,int amt)
	{
		if(amt<=getAcAvailableBalance(fromAcChoice,amt))
		{
			double balance = fundTransfer(toAcChoice, fromAcChoice, amt);
			serviceLogger.info("Fund transfer to Account No "+toAcChoice
					+"from Account No. "+fromAcChoice+". Current Balance of Account No. "
					+fromAcChoice+" is "+balance);
			
			System.out.println("Fund transfer to Account No "+toAcChoice
					+"from Account No. "+fromAcChoice+"\nCurrent Balance of Account No. "
					+fromAcChoice+" is "+balance);
		}
		else
		{
			serviceLogger.warn("Insufficient Balance for A/c No. "+fromAcChoice);
			System.out.println("Insufficient Balance  for A/c No. "+fromAcChoice);
		}
	}
	
	public boolean checkTransactionPassword(String userName, String tnxPassword)
	{
		return bDao.checkTransactionPassword(userName,tnxPassword);
	}

	@Override
	public boolean isUserExist(String username) {
		return bDao.isUserExist(username);
	}
}
