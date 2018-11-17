package com.cb.service;

import java.util.List;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public interface AdminService {

	boolean chechUser(String userId);

	List<Transaction> getPeriodicalTransaction(String startDate, String endDate);

	String addCustomer(Customer cust);

	int addUser(UserTable ut);

	int addAccount(Master am);
	
	public boolean validateCustomerName(String customer_name)
			throws BankingException;

	public boolean validateEmail(String email) throws BankingException;

	public boolean validatAccType(String account_Type) throws BankingException;

	public boolean validateMinBal(double account_Balance) throws BankingException;

	public boolean validateDate(String startDate) throws BankingException;
}
