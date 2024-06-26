package edu.kh.jdbc1;

import java.sql.*;
import java.util.*;

import edu.kh.mode.vo.Emp2;
public class JDBCExample5 {

	public static void main(String[] args) {
		
//		HIRE_DATE -> input
//		input < HIRE_DATE
//		empname, hire_date, gender
		
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		String user = "kh_sh";
		String pw = "kh1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.print("입사일 입력 (YYYY-MM-DD) : ");
			String hireDateInput = sc.next();
			
			conn = DriverManager.getConnection(url, user, pw);
			stmt = conn.createStatement();
			
			String sql = "SELECT EMP_NAME AS \"이름\", TO_CHAR(HIRE_DATE, 'YYYY\"년\" MM\"월\" DD\"일\"') AS \"입사일\", DECODE(SUBSTR(EMP_NO, 8, 1), 1, 'M', 2, 'F', '없음') AS \"성별\" FROM EMPLOYEE"
					+ " WHERE HIRE_DATE < TO_DATE('" + hireDateInput + "'" + ", 'YYYY-MM-DD')";
			
//			SELECT EMP_NAME AS "이름", TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일"') AS "입사일", DECODE(SUBSTR(EMP_NO, 8, 1), 1, 'M', 2, 'F', '없음') AS "성별" FROM EMPLOYEE
//			WHERE HIRE_DATE < TO_DATE('2004-01-01', 'YYYY-MM-DD');
			
			rs = stmt.executeQuery(sql);
			
			List <Emp2> list = new ArrayList<Emp2>();
			
			while(rs.next()) {
				
				String empName = rs.getString("이름");
				String hireDate = rs.getString("입사일");
				String gender = rs.getString("성별");
				
				Emp2 emp = new Emp2();
				emp.setEmpName(empName);
				emp.setHireDate(hireDate);
				emp.setGender(gender.charAt(0));
				
				list.add(emp);
			}
			
			if (list.isEmpty()) {
				System.out.println("없음");
			} else {
				for (Emp2 emp : list) {
					System.out.println(emp);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
