package edu.kh.emp.common;

import java.sql.*;
import java.util.*;
import java.io.*;
public class JDBCTemplate {

	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		try {
			
			if (conn == null || conn.isClosed()) {
				Properties prop = new Properties();
				FileInputStream fis = new FileInputStream("driver.xml");
				
				prop.loadFromXML(fis);
				
				String driver = prop.getProperty("driver");
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String pw = prop.getProperty("pw");
				
				Class.forName(driver);
				
				conn = DriverManager.getConnection(url, user, pw);
				
				conn.setAutoCommit(false);
				
			}
			
			
		} catch (Exception e) {
			System.out.println("connection error");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		
		try{
			if (conn != null && !conn.isClosed()) conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		
		try {
			if (stmt != null && !stmt.isClosed()) stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commit() {
		
		try {
			if (conn != null && !conn.isClosed()) conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void rollback() {
		
		try {
			if (conn != null && !conn.isClosed()) conn.rollback();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
 