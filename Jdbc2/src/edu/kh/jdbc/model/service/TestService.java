package edu.kh.jdbc.model.service;

import java.sql.*;
import java.io.*;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {

	private TestDAO dao = new TestDAO();
	
	public int insert(TestVO vo1) throws Exception{
		
		Connection conn = getConnection();		
		
		int result = dao.insert(conn, vo1);
		
		if(result > 0) commit(conn);
		else rollback(conn);

		close(conn);
		
		return result;
	}

	public int insert(TestVO vo1, TestVO vo2, TestVO vo3) throws Exception{
		
		Connection conn = getConnection();
		
		int result = 0;
		
		
		int result1 = dao.insert(conn, vo1); 
		int result2 = dao.insert(conn, vo2); 
		int result3 = dao.insert(conn, vo3); 
		
		if (result1 > 0 && result2 > 0 && result3 > 0) {
			commit(conn);
			result = 1;
		}
		else rollback(conn);
		
		close(conn);
		
		return result;
	}


	public int update(TestVO vo) throws Exception{
		
		Connection conn = getConnection();
		
//		int result = 0;
		
		int result = dao.update(conn, vo);
		
		if (result > 0) {
			commit(conn);
//			result = 1;
		} else rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int remove(int input) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.remove(conn, input);
		
		if (result>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	
}
