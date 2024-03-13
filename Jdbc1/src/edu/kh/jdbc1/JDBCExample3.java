package edu.kh.jdbc1;


import java.util.*;

import edu.kh.jdbc1.model.vo.Emp;

import java.sql.*;
public class JDBCExample3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "kh_sh";
		String pw = "kh1234";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.print("부서명 입력 : ");
			String input = sc.nextLine();
			String sql = "SELECT EMP_NAME, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE, SALARY" 
					+ " FROM EMPLOYEE LEFT JOIN DEPARTMENT"
					+ " ON DEPT_CODE = DEPT_ID"
					+ " WHERE DEPT_TITLE = '" + input + "'";
			
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			List<Emp> list = new ArrayList<Emp>();
			
			while (rs.next()) {
				String empName = rs.getString("EMP_NAME");
				int salary = rs.getInt("SALARY");
				String deptTitle = rs.getString("DEPT_TITLE");
				Emp emp = new Emp(empName, deptTitle, salary);
				
				list.add(emp);
			}
			
			if(list.isEmpty()) {
				System.out.println("조회 결과 없음");
			} else {
				for (Emp emp : list) {
					System.out.println(emp);
				}
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				
				if (conn != null) conn.close();
				if (stmt != null) stmt.close();
				if (rs != null) rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
