package com.cb.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	
public static Connection getConnection(){
		
	try {		
	Properties props=new Properties();	
    FileInputStream fis= new FileInputStream("database.properties");
		props.load(fis);
		String uname =props.getProperty("username");//key name
		String pwd =	props.getProperty("password");//key name
		String url =props.getProperty("url");//key name	
		Connection con = DriverManager.getConnection(url,uname,pwd);
	//	System.out.println(con);
		return con;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
return null;
}
}
