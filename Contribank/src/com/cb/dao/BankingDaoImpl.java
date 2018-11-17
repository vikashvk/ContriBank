package com.cb.dao;

import java.util.List;

import com.cb.bean.Payee;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;

public class BankingDaoImpl implements BankingDao {

	@Override
	public boolean validateUser(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Integer> getUserAccounts(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> miniStatement(int accNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> detailedStatement(int accNo, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentAddress(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAddress(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePassword(String userName, String oldPass, String newPass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String chequeRequest(int accNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceTracker> getServiceRequestById(String userName,
			int requestID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServiceTracker> getAllServiceRequested(int accNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAcAvailableBalance(int fromAcChoice, int amt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double fundTransfer(int toAcChoice, int fromAcChoice, int amt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Payee> PayeeAccountId(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addPayee(int user_id, int payee_account_id, String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountExist(int account_no, String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkTransactionPassword(String userName, String tnxPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUserExist(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
