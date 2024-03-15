package edu.kh.emp.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
public class EmployeeDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public EmployeeDAO () {
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("query.xml");
			prop.loadFromXML(fis);
			
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
}
