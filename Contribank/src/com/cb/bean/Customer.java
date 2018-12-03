package com.cb.bean;

public class Customer {
	private String name, address, pancard, emailId;

	public Customer(String name, String address, String pancard, String emailId) {
		super();
		this.name = name;
		this.address = address;
		this.pancard = pancard;
		this.emailId = emailId;
	}

	public Customer() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", pancard="
				+ pancard + ", emailId=" + emailId + "]";
	}

}
