package edu.kh.emp.model.dao;

import java.io.*;
import java.util.*;

import edu.kh.emp.common.JDBCTemplate;
import edu.kh.emp.model.vo.Employee;

import java.sql.*;
public class EmployeeDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public EmployeeDAO() {
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("query.xml");
			prop.loadFromXML(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Employee> selectAll(Connection conn) throws Exception{
		
		List<Employee> list = new ArrayList<Employee>();
		
		try { 
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {	
				int empId = rs.getInt("EMP_ID");
				
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				list.add(new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary));
				
			}
			if (list.isEmpty()) {
				System.out.println("No Employee");
			}
		} finally {
			JDBCTemplate.close(stmt);
		}
		return list;
		
		
		
	}

	public int insertEmp(Connection conn, Employee emp) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insert");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());
			pstmt.setDouble(10, emp.getBonus());
			pstmt.setInt(11, emp.getManagerId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Employee findEmp(Connection conn, int empId) throws Exception{
		
		Employee emp = null;
		
		try {
			
			String sql = prop.getProperty("findEmp");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary);
			}
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return emp;
	}
	
	public int updateEmp(Connection conn, Employee emp) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateEmp");
			
			pstmt = conn.prepareStatement(sql);
			
	        pstmt.setString(1, emp.getEmail());
	        pstmt.setString(2, emp.getPhone());
	        pstmt.setInt(3, emp.getSalary());
	        pstmt.setInt(4, emp.getEmpId());
			
			result = pstmt.executeUpdate();
				
			
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteEmp(Connection conn, int empId) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteEmp");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public List<Employee> selectDeptEmp(Connection conn, String input) throws Exception{
		
		List<Employee> list = new ArrayList<Employee>();
		
		try {
			String sql = prop.getProperty("selectDeptEmp");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input);		
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				list.add(new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary));
			}
			if (list.isEmpty()) {
				System.out.println("No Employee");
			}
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public List<Employee> selectSalEmp(Connection conn, int input) throws Exception{
		
		List<Employee> list = new ArrayList<Employee>();
		
		try {
			
			String sql = prop.getProperty("selectSalEmp");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				list.add(new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary));
				
			}if (list.isEmpty()) {
				System.out.println("No Employee");
			}
			
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public List<Map<String, Integer>> selectDeptTotalSal(Connection conn) throws Exception{
		
		List<Map<String, Integer>> deptSalariesList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectDeptTotalSal");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Map<String, Integer> map = new HashMap<>();
				String deptCode = rs.getString("DEPT_CODE");
				int salary = rs.getInt("SALARY");
				
				map.put(deptCode, salary);
	            deptSalariesList.add(map);
			}
			
		} finally {
			JDBCTemplate.close(stmt);
		}
		
		return deptSalariesList;
	}

	public Employee selectEmpNo(Connection conn, String input) throws Exception{

		Employee emp = null;
		String sql = prop.getProperty("selectEmpNo");
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, input);
		
		rs = pstmt.executeQuery();
		try {
			
			while (rs.next()) {
				
				int empId = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String deptTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId, empName, input, email, phone, deptTitle, jobName, salary);
			}
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return emp;
	}

	public Map<String, Double> selectJobAvgSalary(Connection conn) throws Exception{

		String sql = prop.getProperty("selectJobAvgSalary");
		Map<String, Double> map = new HashMap<String, Double>();
		
		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				String jobName = rs.getString("JOB_NAME");
				double avgSalary = rs.getDouble("SALARY");
				
				map.put(jobName, avgSalary);
			}
			
		} finally {
			JDBCTemplate.close(stmt);
		}
		
		return map;
	}
	
}
