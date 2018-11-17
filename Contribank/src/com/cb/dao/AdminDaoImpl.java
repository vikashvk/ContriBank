package com.cb.dao;

import java.util.List;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;

public class AdminDaoImpl implements AdminDao {

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

}
