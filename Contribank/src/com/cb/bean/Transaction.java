package com.cb.bean;

import java.sql.Date;

public class Transaction {
	private int Transaction_ID;
	private int TranAmount, Account_ID;
	private String Tran_description, TransactionType;
	private Date  DateOfTransaction;
	
	
	public Date getDateOfTransaction() {
		return DateOfTransaction;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		DateOfTransaction = dateOfTransaction;
	}
	public int getTransaction_ID() {
		return Transaction_ID;
	}
	public void setTransaction_ID(int transaction_ID) {
		Transaction_ID = transaction_ID;
	}
	
	
	public int getTranAmount() {
		return TranAmount;
	}
	public void setTranAmount(int tranAmount) {
		TranAmount = tranAmount;
	}
	
	
	public int getAccount_ID() {
		return Account_ID;
	}
	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}
	
	
	public String getTran_description() {
		return Tran_description;
	}
	public void setTran_description(String tran_description) {
		Tran_description = tran_description;
	}
	
	
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	
	
	@Override
	public String toString() {
		return "Transactions [Transaction_ID=" + Transaction_ID
				+ ", TranAmount=" + TranAmount + ", Account_ID=" + Account_ID
				+ ", Tran_description=" + Tran_description
				+ ", TransactionType=" + TransactionType
				+ ", DateOfTransaction=" + DateOfTransaction + "]";
	}
	
	
	public Transaction(int tranId,int tranAmount, int account_ID,
			String tran_description, String transactionType) {
		super();
		Transaction_ID=tranId;
		DateOfTransaction = new Date(new java.util.Date().getTime());
		TranAmount = tranAmount;
		Account_ID = account_ID;
		Tran_description = tran_description;
		TransactionType = transactionType;
	}
	
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
