package com.cb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cb.bean.Master;
import com.cb.bean.PayeeTable;
import com.cb.bean.ServiceTracker;
import com.cb.bean.Transaction;
import com.cb.bean.UserTable;
import com.cb.util.DBUtil;

public class BankingDaoImpl implements BankingDao {

	Connection conn;
	Logger logger=Logger.getLogger(BankingDaoImpl.class.getName());

	public BankingDaoImpl() {
		super();
		conn = DBUtil.getConnection();
        PropertyConfigurator.configure("Log4j.properties");
	}


	@Override
	public List<Integer> getUserAccounts(String userName) {
		try {
			String sql = "SELECT Account_ID FROM master WHERE User_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			// String accNo="";
			List<Integer> accNo = new ArrayList<>();
			while (rs.next()) {
				accNo.add(rs.getInt(1));
			}

			return accNo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Transaction> miniStatement(int accNo) {
		List<Transaction> ms=new ArrayList<Transaction>();
		String sql="select * from Transaction where Account_ID=?";
		try{
		PreparedStatement statement=conn.prepareStatement(sql);
		statement.setInt(1,accNo);
		ResultSet rs=statement.executeQuery();
		int count=1;
		while(rs.next()&& count<=10)
		{
			ms.add(new Transaction(rs.getInt(1),rs.getInt(5),rs.getInt(6),rs.getString(2),rs.getString(4)));
		    count++;
		}
		logger.info("Mini statement generated for Account no:"+accNo);
			return ms;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	@Override
	public List<Transaction> detailedStatement(int accNo) {
		List<Transaction> ms=new ArrayList<Transaction>();
		String sql="select * from Transaction where Account_ID=?";
		try{
		PreparedStatement statement=conn.prepareStatement(sql);
		statement.setInt(1,accNo);
		ResultSet rs=statement.executeQuery();
		while(rs.next())
		{
			ms.add(new Transaction(rs.getInt(1),rs.getInt(5),rs.getInt(6),rs.getString(2),rs.getString(4)));
		}
		logger.info("Mini statement generated for Account no:"+accNo);
			return ms;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String getCurrentAddress(String userName) {
		String sql = "SELECT c.Address FROM customer c,usertable u WHERE c.pancard=u.pancard AND u.user_id =?";
		PreparedStatement statement;
		String address = null;
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				address = rs.getString(3);
			}
		} catch (Exception e) {

		}
		return address;
	}

	private int generateServiceId() {

		String sql = "SELECT seq_serviceTracker_id.nextval FROM DUAL";

		Statement stat;
		int serId = 0;
		try {
			stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(sql);
			if (rSet.next())// move record pointer to 1st row
			{
				serId = rSet.getInt(1);// move to first column
				logger.info("New service id generated: "+serId);
			}
			return serId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String updateAddress(String userName, String address) {
		try {
			String sql = "UPDATE customer SET Address=? WHERE pancard=(SELECT pancard FROM usertable where user_id=?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, address);
			statement.setString(2, userName);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("Address updated for username:"+userName);
		return "Successfully updated the address for User:" + userName;
	}

	@Override
	public String changePassword(String userName, String oldPass, String newPass) {
		try {
			String sql = "UPDATE UserTable SET Login_Password=? WHERE User_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, newPass);
			statement.setString(2, userName);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("Password updated for username:"+userName);
		return "Password Changed Successfully";
	}

	@Override
	public String chequeRequest(int accNo) {
		String sql = "INSERT INTO Servicetracker VALUES(?,'Chequebook request',?,sysdate,'Open')";
		int servID;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			servID = generateServiceId();
			statement.setInt(1, servID);
			statement.setInt(2, accNo);
			statement.executeUpdate();
			logger.info("Chequebook requested for Account No:"+accNo);
			return "Your chequebook request is initiated. Your request id :"
					+ servID;
		} catch (Exception b) {
			b.printStackTrace();
		}
		return null;

	}

	@Override
	public List<ServiceTracker> getServiceRequestById(String userName,
			int requestID) {
		try {
			String sql = "SELECT * FROM ServiceTracker WHERE Service_Id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, requestID);
			ResultSet rs = statement.executeQuery();
			List<ServiceTracker> service = new ArrayList<>();

			while (rs.next()) {
				service.add(new ServiceTracker(rs.getInt(1), rs.getInt(3), rs
						.getString(2), rs.getString(5)));

			}

			return service;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<ServiceTracker> getAllServiceRequested(int accNo) {
		try {
			String sql = "SELECT * FROM ServiceTracker WHERE Account_Id =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, accNo);
			ResultSet rs = statement.executeQuery();
			List<ServiceTracker> service = new ArrayList<>();

			while (rs.next()) {
				service.add(new ServiceTracker(rs.getInt(1), rs.getInt(3), rs
						.getString(2), rs.getString(5)));

			}

			return service;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public double getAcAvailableBalance(int fromAcChoice) {
		try {
			String sql = "SELECT * FROM master WHERE Account_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, fromAcChoice);
			ResultSet rs = statement.executeQuery();
			Master master =new Master();
			if(rs.next()) {
				master.setAccount_Balance(rs.getInt(3));
			}
						 return master.getAccount_Balance();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	
	}

	@Override
	public double fundTransfer(int toAcChoice, int fromAcChoice, int amt) {
		double balance = 0;
		try {
			String sql_FromAcc = "UPDATE master SET Account_Balance=(SELECT Account_Balance FROM master where Account_id=?)-? WHERE Account_id=?";
			String sql_ToAcc = "UPDATE master SET Account_Balance=(SELECT Account_Balance FROM master where Account_id=?)+? WHERE Account_id=?";
			PreparedStatement statement_FromAcc = conn
					.prepareStatement(sql_FromAcc);
			statement_FromAcc.setInt(1, fromAcChoice);
			statement_FromAcc.setInt(2, amt);
			statement_FromAcc.setInt(3, fromAcChoice);
			statement_FromAcc.executeUpdate();
			BankingDaoImpl dao = new BankingDaoImpl();

			dao.transaction(fromAcChoice, "debited from " + fromAcChoice
					+ " with " + amt, "d", amt);

			PreparedStatement statement_ToAcc = conn
					.prepareStatement(sql_ToAcc);
			statement_ToAcc.setInt(1, toAcChoice);
			statement_ToAcc.setInt(2, amt);
			statement_ToAcc.setInt(3, toAcChoice);
			statement_ToAcc.executeUpdate();
			dao.transaction(toAcChoice, "credited to " + toAcChoice + " with "
					+ amt, "c", amt);

			BankingDaoImpl impl = new BankingDaoImpl();
			balance = impl.getAcAvailableBalance(fromAcChoice);
			// return balance;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		logger.info("Fund transfer to Account No "+toAcChoice
				+"from Account No. "+fromAcChoice+". Current Balance of Account No. "
				+fromAcChoice+" is "+balance);
		return balance;
	}
	// insert transaction

	public void transaction(int accNo, String txnDescript, String txnType,
			double amt) {
		String sql = "INSERT INTO transaction VALUES(?,?,sysdate,?,?,?)";
		int txnID = 0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			txnID = generateTransactionId();
			statement.setInt(1, txnID);
			statement.setString(2, txnDescript);
			statement.setString(3, txnType);
			statement.setDouble(4, amt);
			statement.setInt(5, accNo);
			statement.executeUpdate();

		} catch (Exception b) {
			b.printStackTrace();
		}

	}
	private int generateTransactionId() {

		String sql = "SELECT tnx_seq_id.nextval FROM DUAL";

		Statement stat;
		int tnxId = 0;
		try {
			stat = conn.createStatement();
			ResultSet rSet = stat.executeQuery(sql);
			if (rSet.next())// move record pointer to 1st row
			{
				tnxId = rSet.getInt(1);// move to first column
			}
			return tnxId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public List<PayeeTable> PayeeAccountId(String userName) {
		try{
			String sql = "SELECT * FROM PayeeTable WHERE User_Id =?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, userName);
		ResultSet rs = statement.executeQuery();
		List<PayeeTable> service = new ArrayList<>();

		while (rs.next()) {
			service.add(new PayeeTable(rs.getString(1), rs
					.getInt(2), rs.getString(3)));
		}
		return service;
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return null;

	}

	@Override
	public String addPayee(String user_id, int payee_account_id, String nickname) {
		String sql = "INSERT INTO PayeeTable VALUES(?,?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user_id);
			statement.setInt(2, payee_account_id);
			statement.setString(3, nickname);
			statement.executeQuery();
			logger.info("New payee account added for Username:"+user_id);
			return nickname;
		} catch (Exception b) {
			b.printStackTrace();
		}
		return null;


	}

	@Override
	public boolean isAccountExist(int account_no, String userName) {
		try {
			
			String sql="SELECT Account_Id FROM master where Account_Id=?";	
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, account_no);
			ResultSet rs = statement.executeQuery();
			Master master = new Master();
			while (rs.next()) {
				master.setAccount_ID(rs.getInt(1));
			}
			 if(account_no==master.getAccount_ID())
			 return true;
			 else
			 return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkTransactionPassword(String userName, String tnxPassword) {
		try {
			String sql = "SELECT Transaction_Password FROM UserTable WHERE User_ID =?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			//UserTable user = new UserTable();
			String password="";
			while (rs.next()) {
				//user.setUser_id(rs.getString(1));
				password=rs.getString(1);
			}
			if (tnxPassword.equals(password))
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
}

	@Override
	public boolean isUserExist(String username) {
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
			if (username.equals(user.getUser_id()))
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean checkOldPassword(String userName,String oldPass) {
		try {
			String query = "Select Login_Password from UserTable where user_ID=?";
			PreparedStatement stat = conn.prepareStatement(query);
			stat.setString(1, userName);
			ResultSet rs =  stat.executeQuery();
			String pass="";
			if(rs.next())
				pass=rs.getString(1);
			if(pass.equals(oldPass))
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

}
