package com.cb.service;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.UserTable;
import com.cb.exception.BankingException;

public interface AdminService {

	String addCustomer(Customer cust);

	String addUser(UserTable ut);

	int addAccount(Master am);
	
	/**
	 * @param customer_name
	 * to validate the customer name which is of only alphabets
	 * @throws BankingException --will display exception message
	 */
	public boolean validateCustomerName(String customer_name)
			throws BankingException;

	/**
	 * @param email
	 * validate the email id which has alphanumeric and @ 
	 * @throws BankingException --will display exception message
	 */
	public boolean validateEmail(String email) throws BankingException;

	/**
	 * @param account_Type
	 *  to validate the Account type is Savings or current
	 * @throws BankingException --will display exception message
	 */
	public boolean validatAccType(String account_Type) throws BankingException;

	/**
	 * @param account_Balance
	 *  to verify the minimum balance 
	 * @throws BankingException --will display exception message
	 */
	public boolean validateMinBal(double account_Balance) throws BankingException;

	/**
	 * @param startDate
	 *  to verify the date format
	 * @throws BankingException --will display exception message
	 */
	public boolean validateDate(String startDate) throws BankingException;

	/**
	 * @param userId
	 * to validate the user id which is alphanumeric with length
	 * @throws BankingException --will display exception message
	 */
	boolean validateUserId(String userId) throws BankingException;
}
