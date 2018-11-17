package com.cb.bean;

public class Payee {
	
	private int Payee_Account_Id;
	private int User_Id;
	private String Nick_name;
	
	
	public Payee() {
		super();
	}	
	
	public Payee( int user_Id,int payee_Account_Id, String nick_name) {
		super();
		User_Id = user_Id;
		Payee_Account_Id = payee_Account_Id;
		Nick_name = nick_name;
	}


	@Override
	public String toString() {
		return "PayeeTable [Payee_Account_Id=" + Payee_Account_Id
				+ ", User_Id=" + User_Id + ", Nick_name=" + Nick_name + "]";
	}

	public int getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(int user_Id) {
		User_Id = user_Id;
	}

	public int getPayee_Account_Id() {
		return Payee_Account_Id;
	}
	public void setPayee_Account_Id(int payee_Account_Id) {
		Payee_Account_Id = payee_Account_Id;
	}
	
	
	public String getNick_name() {
		return Nick_name;
	}
	public void setNick_name(String nick_name) {
		Nick_name = nick_name;
	}
	
}
