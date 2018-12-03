package com.cb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cb.bean.Admin;
import com.cb.bean.UserTable;
import com.cb.util.DBUtil;

public class LoginDaoImpl implements LoginDao {

	Connection conn;
	Logger logger=Logger.getLogger(LoginDaoImpl.class.getName());
	public LoginDaoImpl() {

		super();
		conn = DBUtil.getConnection();
        PropertyConfigurator.configure("Log4j.properties");
	}

	@Override
	public boolean verifyCredentials(String username, String password) {
		try {
			String sql = "SELECT * FROM UserTable WHERE User_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			UserTable user = new UserTable();
			while (rs.next()) {
				user.setUser_id(rs.getString(1));
				user.setLogin_password(rs.getString(2));
			}
			if (username.equals(user.getUser_id())
					&& password.equals(user.getLogin_password()))
				{logger.info("User logged in with user id:"+username);
				return true;}
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
return false;
		}

	}

	@Override
	public String checklockstatus(String username, String password) {
		try {
			String sql = "SELECT * FROM UserTable WHERE User_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			UserTable user = new UserTable();
			String status;
			while (rs.next()) {
				user.setLock_status(rs.getString(5));
			}
			status=user.getLock_status();
			if (status.equals("o"))
				return "o";
			else
				return "b";
		} catch (SQLException e) {
			e.printStackTrace();
return "b";
		}
	}

	@Override
	public void lockAccount(String username) {
		try {
			String sql = "UPDATE UserTable SET Lock_Status='b' WHERE User_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.executeUpdate() ;
			logger.info("Account has been locked for the username: "+username);
		} catch (SQLException e) {
			e.printStackTrace();
		}		

	}

	@Override
	public String checkSecretAns(String username) {
    try{
	    String sql = "SELECT * FROM UserTable WHERE User_ID =?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		UserTable user = new UserTable();
		String ans;
		while (rs.next()) {
			user.setSecret_question(rs.getString(3));
		}
		ans=user.getSecret_question();
			return ans;
	} catch (SQLException e) {
		e.printStackTrace();
return null;
	}
	}

	@Override
	public void setpassword(String username, String string) {
		try {
			String sql = "UPDATE UserTable SET Login_Password=? WHERE User_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, string);
			statement.setString(2, username);
			statement.executeUpdate() ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	

	}

	@Override
	public boolean adminLogin(int id, String password) {

		try {
			String sql = "SELECT * FROM admin WHERE adminId =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			Admin admin = new Admin();
				while (rs.next()) {
				admin.setAdminId(rs.getInt(1));
				admin.setAdminPassword(rs.getString(2));
			}
			if (id == admin.getAdminId()
					&& password.equals(admin.getAdminPassword()))
			{
				logger.info("Admin logged in with admin id:"+id);

				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;

	}

	@Override
	public void updateUser(UserTable ut, String username) {
		
		try {
			String sql = "UPDATE usertable SET Login_Password=?,Secret_Question=?,Transaction_Password=? WHERE User_Id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ut.getLogin_password());
			statement.setString(2, ut.getSecret_question());
			statement.setString(3, ut.getTransaction_password());
			statement.setString(4, username);
			statement.executeUpdate() ;
			logger.info("User details has been updated for username:"+username);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
