package com.cb.dao;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.UserTable;

public interface AdminDao {

	/**
	 * @param cust is the  details of the customer belongs to this bank
	 * @return cust.getName()
	 */
	public String addCustomer(Customer cust);

	/**
	 * @param ut is the User table holds User's id, login password, secret question, transaction, pancard no
	 * @return userID
	 */
	public String addUser(UserTable ut);

	/**
	 * @param am is the account master which has Account id,type, balance,open date and id of the customer
	 * @return accNo
	 */
	public int addAccount(Master am);

}
