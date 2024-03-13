package edu.kh.jdbc1;

import java.sql.*;
public class JDBCExample1 {

	public static void main(String[] args) {
		
//		JDBC(Java DataBase Connectivity) - Java Programming API for DB
		
//		1.Java - JDBC interface
//		2.DBMS (Oracle)
//		3.Oracle - Java library
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
//		Connect from conn
//		String type = "jdbc:oracle:thin:@";
//		String ip = "localhost";
//		== 127.0.0.1 (loop back ip)
		
//		String port = ":1521";
//		String sid = ":XE";
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		String user = "kh_sh";
		String pw = "kh1234";
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, user, pw);
			
			System.out.println(conn);
			
			String sql = "SELECT EMP_ID, EMP_NAME, SALARY, HIRE_DATE FROM EMPLOYEE";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
//			rs.getArray(0);
//			byte h = rs.getByte(sql);
//			rs.getArray(h);
			while(rs.next()) {
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				Date hireDate = rs.getDate("HIRE_DATE");				
				System.out.printf("사번 : %s / 이름 : %s / 급여 : %d / 입사일 : %s \n",
						empId, empName, salary, hireDate);
			}
			
		
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC driver path error");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
