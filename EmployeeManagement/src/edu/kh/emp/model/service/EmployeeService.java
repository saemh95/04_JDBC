package edu.kh.emp.model.service;

import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;
import static edu.kh.emp.common.JDBCTemplate.*;
import java.sql.*;
import java.util.*;
public class EmployeeService {

	private EmployeeDAO dao = new EmployeeDAO();

	public List<Employee> selectAll() throws Exception{
		
		Connection conn = getConnection();
		List<Employee> empList = dao.selectAll(conn);
		return empList;
	}

	public int insertEmp(Employee emp) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertEmp(conn, emp);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public Employee findEmp(int empId) throws Exception{

		Connection conn = getConnection();
		Employee emp = dao.findEmp(conn, empId);
		
		close(conn);
		
		return emp;
	}

	public int updateEmp(Employee emp) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateEmp(conn, emp);
		
		if (result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public int deleteEmp(int empId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteEmp(conn, empId);
		
		if (result > 0 )commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public List<Employee> selectDeptEmp(String input) throws Exception{

		Connection conn = getConnection();
		
		List<Employee> empList = dao.selectDeptEmp(conn, input);
		return empList;
	}

	public List<Employee> selectSalEmp(int input) throws Exception{
		
		Connection conn = getConnection();
		
		List<Employee> empList = dao.selectSalEmp(conn, input);
		
		return empList;
	}

	public List<Map<String, Integer>> selectDeptTotalSal() throws Exception{
		
		Connection conn = getConnection();
		
		if (dao.selectDeptTotalSal(conn).isEmpty()) {
			System.out.println("Error");
		}
		return dao.selectDeptTotalSal(conn);
	}

	public Employee selectEmpNo(String input) throws Exception{

		Connection conn = getConnection();
		
		Employee emp = dao.selectEmpNo(conn, input);
		return emp;
	}

	public Map<String, Double> selectJobAvgSalary() throws Exception{

		Connection conn = getConnection();
		
		Map<String, Double> map = dao.selectJobAvgSalary(conn);
		
		return map;
	}
	
}
