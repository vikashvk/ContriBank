package com.cb.service;

import java.util.List;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public class AdminServiceImpl implements AdminService {

	@Override
	public boolean validateCustomerName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateEmail(String emailId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean chechUser(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Transaction> getPeriodicalTransaction(String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCustomer(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUser(UserTable ut) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addAccount(Master am) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean validatAccType(String account_Type) throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateMinBal(double account_Balance)
			throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateDate(String startDate) throws BankingException {
		// TODO Auto-generated method stub
		return false;
	}

}
