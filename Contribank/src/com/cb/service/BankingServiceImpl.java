package com.cb.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.cb.bean.PayeeTable;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;
import com.cb.dao.BankingDao;
import com.cb.dao.BankingDaoImpl;

public class BankingServiceImpl implements BankingService {

	BankingDao bDao = new BankingDaoImpl();
	Scanner sc = new Scanner(System.in);
	
	@Override
	public List<Integer> getUserAccounts(String userName) {
		return bDao.getUserAccounts(userName);
	}

	@Override
	public List<Transaction> miniStatement(int accNo) {
		return bDao.miniStatement(accNo);	
	}

	@Override
	public List<Transaction> detailedStatement(int accNo) {
		return bDao.detailedStatement(accNo);
	}

	@Override
	public int getCurrentAcNo(String userName) {
		//fetching all the accounts of current user -- here its 1001
		List<Integer> accList = getUserAccounts(userName);
		for(Integer a:accList)
			System.out.println(a);
		
		//storing the ac no. for which the user requested for operations
		System.out.println("Select the desired account number:");
		
		int acChoice=0;
		do {
            try {
            	acChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter account no in digits only.");
            }
            sc.nextLine(); // clears the buffer
        } while (acChoice <= 0);
		
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
	public String updateAddress(String userName, String address) {
		return bDao.updateAddress(userName, address);
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
	public double getAcAvailableBalance(int fromAcChoice) {

		return bDao.getAcAvailableBalance(fromAcChoice);
	}


	public double fundTransfer(int toAcChoice,int fromAcChoice,int amt)
	{
		return bDao.fundTransfer(toAcChoice,fromAcChoice, amt);
	}
	public List<PayeeTable> getPayeeAccountId(String userName)
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
		int toAc=0;
		do
		{
			
			do {
	            try {
	            	toAc = sc.nextInt();
	            } catch (InputMismatchException e) {
	                System.out.println("Enter amount in numbers:");
	            }
	            sc.nextLine(); // clears the buffer
	        } while (toAc <= 0);
			
			
			

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
		if(amt<=getAcAvailableBalance(fromAcChoice))
		{
			double balance = fundTransfer(toAcChoice, fromAcChoice, amt);

			
			System.out.println("Fund transferd to Account No "+toAcChoice
					+" from Account No. "+fromAcChoice+"\nCurrent Balance of Account No. "
					+fromAcChoice+" is "+balance);
		}
		else
		{
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

	@Override
	public boolean checkOldPassword(String userName,String oldPass) {
		// TODO Auto-generated method stub
		return bDao.checkOldPassword(userName,oldPass);
	}
}
