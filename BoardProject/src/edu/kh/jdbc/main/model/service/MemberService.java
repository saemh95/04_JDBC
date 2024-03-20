package edu.kh.jdbc.main.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.main.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {
	private MemberDAO dao = new MemberDAO();

	public List<Member> selectMemberList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> list = dao.selectMemberList(conn);
		
		close(conn);
		
		return list;
	}

	public int updateMember(String name, String gender, int memberNo) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, name, gender, memberNo);
		
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		
		
		return result;
	}

//	public int updatePassword(int memberNo, String updatePw)throws Exception {
//		Connection conn = getConnection();
//		
//		int result = dao.updatePassword(conn, memberNo, updatePw);
//		
//		
//		if(result > 0 ) {
//			commit(conn);
//		}else {
//			rollback(conn);
//		}
//		
//		
//		
//		return 0;
//	}
	
	public int updatePassword(String current, String newPw1, int memberNo) throws Exception{
	      
	      Connection conn = getConnection();
	      
	      int result = dao.updatePassword(conn, current, newPw1, memberNo);
	      
	      if (result>0) commit(conn);
	      else rollback(conn);
	      
	      close(conn);
	      
	      return result;
	      
	   }

	   public String createSecurityCode() {
	      
	      StringBuffer code = new StringBuffer();
	      
	      Random ran = new Random();
	      
	      for (int i=0; i<6; i++) {
	         
	         int x = ran.nextInt(10);
	         
	         code.append(x);
	      }
	      
	      return code.toString();
	   }


//	public Member findRow(int memberNo) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		Member member = dao.findRow(conn, memberNo);
//		
//		
//		return member;
//	}

	/** 회원 탈퇴 서비스
	 * @param memberPw
	 * @param memberNo
	 * @return
	 */
	public int unRegisterMember(String memberPw, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		int result = dao.unRegisterMember(conn, memberPw, memberNo);
		
		if(result > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return result;
	}
}
