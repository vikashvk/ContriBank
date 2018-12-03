package com.cb.bean;

public class Fund_transfer {

	private int FundTransfer_ID, Account_ID, Payee_Account_ID, Transfer_Amount;

	public Fund_transfer() {
		super();

	}

	public Fund_transfer(int fundTransfer_ID, int account_ID,
			int payee_Account_ID, int transfer_Amount) {
		super();
		FundTransfer_ID = fundTransfer_ID;
		Account_ID = account_ID;
		Payee_Account_ID = payee_Account_ID;
		Transfer_Amount = transfer_Amount;
	}

	@Override
	public String toString() {
		return "Fund_Transfer [FundTransfer_ID=" + FundTransfer_ID
				+ ", Account_ID=" + Account_ID + ", Payee_Account_ID="
				+ Payee_Account_ID + ", Transfer_Amount=" + Transfer_Amount
				+ "]";
	}

	public int getFundTransfer_ID() {
		return FundTransfer_ID;
	}

	public void setFundTransfer_ID(int fundTransfer_ID) {
		FundTransfer_ID = fundTransfer_ID;
	}

	public int getAccount_ID() {
		return Account_ID;
	}

	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}

	public int getPayee_Account_ID() {
		return Payee_Account_ID;
	}

	public void setPayee_Account_ID(int payee_Account_ID) {
		Payee_Account_ID = payee_Account_ID;
	}

	public int getTransfer_Amount() {
		return Transfer_Amount;
	}

	public void setTransfer_Amount(int transfer_Amount) {
		Transfer_Amount = transfer_Amount;
	}

}