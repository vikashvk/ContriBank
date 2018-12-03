package com.cb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cb.bean.Customer;
import com.cb.bean.Master;
import com.cb.bean.UserTable;
import com.cb.util.DBUtil;

public class AdminDaoImpl implements AdminDao {

	Connection conn;
    Logger logger=Logger.getLogger(AdminDaoImpl.class.getName());
	public AdminDaoImpl() {
		super();
		conn = DBUtil.getConnection();
        PropertyConfigurator.configure("Log4j.properties");
	}
	@Override
	public String addCustomer(Customer cust) {
		String sql="INSERT INTO customer VALUES(?,?,?,?)";
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,cust.getName());
			statement.setString(2,cust.getEmailId());
			statement.setString(3, cust.getAddress());
			statement.setString(4,cust.getPancard());
			statement.executeUpdate();	
			logger.info("New customer added.");
		}
		catch(Exception b)
		{
			b.printStackTrace();
		}
return cust.getName();
	}

	@Override
	public String addUser(UserTable ut) {
		String sql="INSERT INTO UserTable (User_Id,Login_Password,Lock_Status,Pancard) VALUES(?,?,?,?)";
		String userID="";
		try{
			PreparedStatement statement = conn.prepareStatement(sql);
			userID="User"+generateUserId();
			statement.setString(1,userID);
			statement.setString(2,ut.getLogin_password());
			statement.setString(3, ut.getLock_status());
			statement.setString(4,ut.getPancard());
			statement.executeUpdate();	
			logger.info("New user added with user id:"+userID);
		}
		catch(Exception b)
		{
			b.printStackTrace();
		}
        return userID;
	}

private int generateUserId() {
		
		String sql="SELECT seq_user_id.nextval FROM DUAL";
		
		Statement stat;
		int userId=0;
		try {
			stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(sql);
			if(rSet.next())//move record pointer to 1st row
			{	
				userId = rSet.getInt(1);//move to first column		
				logger.info("New user id generated: "+userId);
			}
			return userId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		}

	private int generateAccountNo() {
		
		String sql="SELECT seq_account_id.nextval FROM DUAL";
		
		Statement stat;
		int accId=0;
		try {
			stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(sql);
			if(rSet.next())//move record pointer to 1st row
			{	
				accId = rSet.getInt(1);//move to first column
				logger.info("New account no generated:"+accId);
			}
			return accId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		}
	
	@Override
	public int addAccount(Master am) {

		int accNo=0;
		String sql="INSERT INTO master VALUES(?,?,?,sysdate,?)"; 
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			accNo=generateAccountNo();
			statement.setInt(1,accNo);
			statement.setString(2,am.getAccount_Type());
			statement.setDouble(3,am.getAccount_Balance());
			statement.setString(4,am.getUser_ID());
			statement.executeUpdate();	
			BankingDaoImpl dao=new BankingDaoImpl();
			dao.transaction(accNo, "Credited with amount "+am.getAccount_Balance(), "c", am.getAccount_Balance());
			logger.info("New account created with Account no:"+accNo);
			}
		catch (SQLException e) {	
			e.printStackTrace();	
		}
		return accNo;
	}

}
