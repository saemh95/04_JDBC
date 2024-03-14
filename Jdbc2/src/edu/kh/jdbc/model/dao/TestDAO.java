package edu.kh.jdbc.model.dao;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

import java.io.*;
import java.sql.*;
import java.util.*;
public class TestDAO {

	
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public TestDAO() {
		
//		FileOutputStream fos = null;
		FileInputStream fis = null;
//		ObjectOutputStream oos = null;
//		ObjectInputStream ois = null;
		try {
			prop = new Properties();
			fis = new FileInputStream("test-query.xml");
			prop.loadFromXML(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int insert(Connection conn, TestVO vo1) throws Exception{
		
		int result = 0;
		
		String sql = prop.getProperty("insert");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo1.getTestNo());
			pstmt.setString(2, vo1.getTestTitle());
			pstmt.setString(3, vo1.getTestContent());
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt); 
		}
		
		return result;
	}

	public int update(Connection conn, TestVO vo) throws Exception{
		
		int result = 0;
		
		
		try {
			String sql = prop.getProperty("update");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTestTitle());
			pstmt.setString(2, vo.getTestContent());
			pstmt.setInt(3, vo.getTestNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}

	public int remove(Connection conn, int input) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("remove");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	
	
	
	
}
