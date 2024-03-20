package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberDAO {
	
	//JDBC 객체 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	//기본생성자 member-sql.xml 읽어오고 prop 저장
	public MemberDAO() {
		try {		
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		

	}

	public List<Member> selectMemberList(Connection conn) throws Exception{
		
		List<Member> list = new ArrayList<Member>();
		
		try {
			String sql = prop.getProperty("selectMemberList");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("MEMBER_ID"));
				member.setMemberName(rs.getString("MEMBER_NM"));
				member.setMemberGender(rs.getString("성별"));
				
				list.add(member);
			}
			
			
//			private int memberNo; // 회원 번호
//			private String memberId; // 회원 아이디
//			private String memberPw; // 회원 비밀번호
//			private String memberName; // 회원 이름
//			private String memberGender; // 회원 성별
//			private String enrollDate; // 가입일
//			private String unregisterFlag; // 탈퇴 여부
			
		}finally{
			JDBCTemplate.close(stmt);
		}
		
		
		
		return list;
	}

	public int updateMember(Connection conn, String name, String gender, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateMember");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2,  gender);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			

			
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

//	public int updatePassword(Connection conn, int memberNo, String updatePw) throws Exception{
//		
//		int result = 0 ;
//		
//		try {
//			String sql = prop.getProperty("updatePassword");
////			UPDATE MEMBER SET
////			MEMBER_PW = ?
////			WHERE MEMBER_NO = ?
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, updatePw);
//			pstmt.setInt(2, memberNo);
//			result = pstmt.executeUpdate();
//			
//			
//			
//		}finally {
//			
//			JDBCTemplate.close(pstmt);
//			
//		}
//		
//		
//		return result;
//	}
//
//	public Member findRow(Connection conn, int memberNo) throws Exception{
//		// TODO Auto-generated method stub
//		
//		Member member = null;
//		
//		try {
//			String sql = prop.getProperty("findRow");
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, memberNo);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				member = new Member();
//				member.setMemberPw(rs.getString("MEMBER_PW"));
//			
//			}
//			
//			
//		}finally {
//			
//			
//		}
//		
//		
//		
//		return member;
//	}
		
	   public int updatePassword(Connection conn, String current, String newPw1, int memberNo) throws Exception{

		      int result = 0;
		      
		      try {
		         
		         String sql = prop.getProperty("updatePassword");
		         
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, newPw1);
		         pstmt.setString(2, current);
		         pstmt.setInt(3, memberNo);
		         
		         result = pstmt.executeUpdate();
		         
		      } finally {
		         JDBCTemplate.close(pstmt);
		      }
		      
		      return result;
		   }

	/** 회원 탈퇴 SQL 수행 DAO
	 * @param conn
	 * @param memberPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int unRegisterMember(Connection conn, String memberPw, int memberNo) throws Exception{
	
		int result = 0;
		try {
			String sql = prop.getProperty("unRegisterMember");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
}
