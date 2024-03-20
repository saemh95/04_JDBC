package edu.kh.jdbc.board.model.dto;

public class Comment {
	
	private int commentNo;			//댓글번호
	private String commentContent;  //댓글내용
	private int memberNo; 			//작성자 회원 번호
	private String memberName;		//작성자 회원 이름
	private String createDate;		//댓글 작성일
	private int boardNo;			//작성된 게시글 번호 -- 왜? 어느글에 있는 댓글을 등록할건지 수정할건지 삭제할건지 기록해야놔야 할거 아니야.
	
	//기본생성자
	public Comment() {
		super();
	}
	
	//getter setter
	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	
	
	
	
	

}
