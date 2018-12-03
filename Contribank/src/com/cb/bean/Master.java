package com.cb.bean;

import java.util.Date;

public class Master {
	private int Account_ID;
	private String User_ID;
	private double Account_Balance;
	private String Account_Type;
	Date open_date;

	public int getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String User_ID) {
		this.User_ID = User_ID;
	}

	public double getAccount_Balance() {
		return Account_Balance;
	}

	public void setAccount_Balance(double account_Balance2) {
		Account_Balance = account_Balance2;
	}

	public String getAccount_Type() {
		return Account_Type;
	}

	public void setAccount_Type(String account_Type) {
		Account_Type = account_Type;
	}

	public Date getOpen_date() {
		return open_date;
	}

	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}

}