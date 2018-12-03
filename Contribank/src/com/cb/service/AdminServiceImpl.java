package com.cb.service;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.UserTable;
import com.cb.dao.AdminDao;
import com.cb.dao.AdminDaoImpl;
import com.cb.exception.BankingException;

public class AdminServiceImpl implements AdminService {

	static Scanner sc = new Scanner(System.in);
	AdminDao adao = new AdminDaoImpl();
	@Override
	public boolean validateCustomerName(String name) throws BankingException {
		String namePattern = "[A-Z][a-z]+";
		if(Pattern.matches(namePattern, name))
			return true;
		else
			throw new BankingException("Invalid Customer Name. Name should start with capital letter and only Characters allowed.");
		
	}

	@Override
	public boolean validateEmail(String emailId) throws BankingException {
		String mailPattern = "[a-z0-9]+@[a-z]+.[a-z]{2,3}";
		if(Pattern.matches(mailPattern, emailId)) 
			return true;
		else
			throw new BankingException("Invalid Email id.");
	}

	@Override
	public String addCustomer(Customer cust) {
		return adao.addCustomer(cust);
	}

	@Override
	public String addUser(UserTable ut) {
		return adao.addUser(ut);
	}

	@Override
	public int addAccount(Master am) {
		return adao.addAccount(am);
	}

	@Override
	public boolean validatAccType(String account_Type) throws BankingException {
		if(account_Type.equalsIgnoreCase("Savings") || account_Type.equalsIgnoreCase("current"))
			return true;
		else
			throw new BankingException("Account Type can be either Savings / Current");
	}

	@Override
	public boolean validateMinBal(double account_Balance)
			throws BankingException {
		if(account_Balance > 3000.0)
			return true;
		else
			throw new BankingException("Minimum opening balance cannot be less than 3000.0");
	}

	@Override
	public boolean validateDate(String startDate) throws BankingException {
		String dateFormat = "^(([0-9])|([0-2][0-9])|([3][0-1]))"+"-(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"+"-[0-9]{2}$";
		if(Pattern.matches(dateFormat, startDate)) 
			return true;
		else
			throw new BankingException("Invalid Date format. Please enter date in DD-MMM-YY format");
	}

	@Override
	public boolean validateUserId(String userId) throws BankingException {
		String mailPattern = "[a-zA-Z0-9_]{4,10}";
		if(Pattern.matches(mailPattern, userId)) 
			return true;
		else
			throw new BankingException("Invalid User id.");
	}

}
