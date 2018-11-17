package com.cb.dao;

import java.util.List;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;

public interface AdminDao {
	public String addCustomer(Customer cust);

	public int addUser(UserTable ut);

	public int addAccount(Master am);

	public boolean chechUser(String userId);
	
	public List<Transaction> getPeriodicalTransaction(String startDate, String endDate) ;
}
