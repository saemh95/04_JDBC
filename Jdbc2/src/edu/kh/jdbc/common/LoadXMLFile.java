package edu.kh.jdbc.common;

import java.io.*;
import java.sql.*;
import java.util.*;

public class LoadXMLFile {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			Properties prop = new Properties();
			
			FileInputStream fis = new FileInputStream("driver.xml");
			
			prop.loadFromXML(fis);
//			String url = (String) prop.get("url");
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String pw = prop.getProperty("pw");
			
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, pw);
			
			System.out.println(conn);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
