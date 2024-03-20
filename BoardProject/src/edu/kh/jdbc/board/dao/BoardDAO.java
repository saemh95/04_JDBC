package edu.kh.jdbc.board.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	
	private ResultSet rs;
	private Properties prop = null;

	public BoardDAO() {
		//기본생성자
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}		
	
	}
	
	
	public List<Board> selectAllBoard(Connection conn) throws Exception{
		//결과 저장용 객체 생성
		List<Board> boardList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("selectAllBoard");
			
			//SQL 수행 후 결과 반환받기
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 1행씩 접근하여 컬럼값을 얻어와 옮겨담기
			while(rs.next()) {
				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				String createDate = rs.getString("CREATE_DT");
				int readCount = rs.getInt("READ_COUNT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCommentCount(commentCount);
				
				boardList.add(board); // list에 추가
				
			}
		}finally {
			//JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		
		return boardList;
	}


	/** 게시글 상세 조회하는 SQL 수행할 DAO
	 * @param conn
	 * @param input
	 * @param memberNo
	 * @return board
	 */
	public Board selectBoard(Connection conn, int input, int memberNo) throws Exception{
		
		Board board = null;
		
		try {
			String sql  = prop.getProperty("selectBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
			
		}
		return board;
	}


	/** 조회수 증가 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @return
	 */
	public int updateReadCount(Connection conn, int input) throws Exception{
		//조회수 증가
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateReadCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
	
		return result;
	}


	/** 게시글 수정 SQL 수행 DAO
	 * @param conn
	 * @param boardTitle
	 * @param boardContent
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, String boardTitle, String boardContent, int boardNo) throws Exception{

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateBoard");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	/** 다음 게시글 번호 조회 SQL 수행 DAO
	 * @param conn
	 * @return boardNo
	 */
	public int nextBoardNo(Connection conn) throws Exception{

		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
			
		}finally {
			close(stmt);
			close(rs);
		}
		
		return boardNo;
	}


	public int insertBoard(Connection conn, String boardTitle, String boardContent, int memberNo, int boardNo) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			pstmt = conn.prepareStatement(sql);
			
//			INSERT INTO "BOARD" VALUES (?, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?)
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, boardTitle);
			pstmt.setString(3, boardContent);
			pstmt.setInt(4, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		return result;
	}


	public int deleteBoard(Connection conn, int boardNo, int memberNo) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
//			UPDATE "BOARD" SET
//			DELETE_FL = 'Y'
//			WHERE BOARD_NO = ?
//			AND MEMBER_NO = ?
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
}
