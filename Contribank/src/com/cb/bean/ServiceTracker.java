package com.cb.bean;

public class ServiceTracker {
	private int Service_ID;
	private int Account_ID;
	private String Service_Description, Service_status;
	
	
	public ServiceTracker() {
		super();
	}
	
	
	public ServiceTracker(int service_ID,int account_ID,
			String service_Description, String service_status) {
		super();
		Service_ID = service_ID;
		Account_ID = account_ID;
		Service_Description = service_Description;
		Service_status = service_status;
	}
	
	
	@Override
	public String toString() {
		return "ServiceTracker [Service_ID=" + Service_ID + ", Account_ID="
				+ Account_ID + ", Service_Description=" + Service_Description
				+ ", Service_status=" + Service_status + "]";
	}
	
	
	public int getService_ID() {
		return Service_ID;
	}
	public void setService_ID(int service_ID) {
		Service_ID = service_ID;
	}
	
	
	public int getAccount_ID() {
		return Account_ID;
	}
	public void setAccount_ID(int account_ID) {
		Account_ID = account_ID;
	}
	
	
	public String getService_Description() {
		return Service_Description;
	}
	public void setService_Description(String service_Description) {
		Service_Description = service_Description;
	}
	
	
	public String getService_status() {
		return Service_status;
	}
	public void setService_status(String service_status) {
		Service_status = service_status;
	}
	
}
