package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MemberService;
import edu.kh.jdbc.main.view.MainView;
import edu.kh.jdbc.member.model.dto.Member;

/** 회원 전용 화면
 * 
 */
public class MemberView {
	private Scanner sc = new Scanner(System.in);
	
	private MemberService service = new MemberService();
	
	/**
	 * 회원 기능 메뉴 View
	 * 
	 */
	public void memberMenu() {
		int input = 0;
		do {
			try {
				System.out.println("\n===회원 기능===\n");
				System.out.println("1. 내 정보 조회");
				System.out.println("2. 회원 목록 조회(아이디, 이름, 성별)");
				System.out.println("3. 내 정보 수정(이름, 성별)"); 
				// 현재로그인한 사람의 정보 수정. 
				// db에 업데이트했다면, 로그인멤버도 동기화 시켜줘야 함.
				System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
				System.out.println("5. 회원 탈퇴(보안코드, 비밀번호, UPDATE)");
				
				System.out.println("9. 메인 메뉴로 돌아가기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch(input) {
				case 1: selectMyInfo(); break;
				case 2: selectMemberList(); break;
				case 3: updateMember(); break;
				case 4: updatePassword(); break;
				case 5: if(unRegisterMenu() ) return; break;
				case 9: System.out.println("\n====메인 메뉴로 돌아갑니다====\n"); break;
				case 0: System.out.println("\n====프로그램 종료====\n");
				        //JVM 강제 종료 구문
						//매개변수는 기본 0, 다른 숫자는 오류를 의미
						System.exit(0);
				default : System.out.println("\n***메뉴 번호만 입력해주세요***\n");
				}
				
			}catch(InputMismatchException e) {
				System.out.println("\n***입력 형식이 올바르지 않습니다 ***\n");
				sc.nextLine();
				input = -1;
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		}while(input != 9);
		
	}
	
	
//	private void updatePassword() throws Exception {
//		//System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
//		
//		//현재 비밀번호 구하기.
//		
//		System.out.println("현재 비밀번호를 입력해주세요");
//		String inputPw = sc.next();
//		
//		//여기서 쿼리 보내야 되는 구나. 
//		Member member = service.findRow(Session.loginMember.getMemberNo());
//		String findPw = member.getMemberPw();
//		
//		
//		if(!inputPw.equals(findPw)) {
//			// 일치하지 않는다면, 
//			System.out.println(inputPw);
//			System.out.println(Session.loginMember.getMemberPw());
//			System.out.println("비밀번호가 일치하지 않아 비밀번호 변경이 불가능합니다.");
//			return;
//		} else {
//			//일치한다면, 	
//			String updatePw;
//			String DupUpdatePw;
//			while(true) {
//				System.out.println("새 비밀번호를 입력해주세요");
//				updatePw = sc.next();
//				System.out.println("새 비밀번호 확인");
//				DupUpdatePw = sc.next();
//				
//				if(updatePw.equals(DupUpdatePw)) {
//					break;
//				}
//				System.out.println("새 비밀번호 != 새비밀번호 확인");
//			}
//			
//			int result = service.updatePassword(Session.loginMember.getMemberNo(), updatePw);
//			
//			if(result > 0) {
//				System.out.println("비밀번호가 성공적으로 업데이트 되었습니다. ");
//				// 여기서는 현재 애플리케이션에 있는 비밀번호도 바꾸어주어야겠지.
//				Session.loginMember.setMemberPw(updatePw);
//				
//			} else {
//				System.out.println("비밀번호 변경 중 오류 발생");
//			}
//			
//			
//			
//		} 	
//	}
	
	public void updatePassword() {
	      
	      System.out.println("\n=== 비밀번호 변겅 ===\n");
	      System.out.print("비밀번호 입력 : ");
	      String current = sc.next();
	      
	      String newPw1 = null;
	      
	      while (true) {
	         
	         System.out.println("새 비밀번호 : ");
	         newPw1 = sc.next();
	         
	         System.out.println("새 비밀번호 확인 : ");
	         String newPw2 = sc.next();
	         
	         if(newPw1.equals(newPw2)) break;
	         
	         System.out.println("\n***새 비밀번호가 일치하지 않습니다***\n");
	         
	      }
	      
	      try {
	         
	         int result = service.updatePassword(current, newPw1, Session.loginMember.getMemberNo());
	         
	         if(result>0) System.out.println("\n==== 비밀번호가 변경되었습니다 ====\n");
	         else System.out.println("\n**** 현재 비밀번호가 일치하지 않습니다 ****\n");
	         
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("\n***비밀번호 변경 중 예외 발생***\n");
	         
	      }
	      
	   }
	   
	   public boolean unRegisterMenu() {
	      
	      System.out.println("\n=== 회원 탈퇴 ===\n");
	      
	      System.out.print("비밀번호 입력 : ");
	      String memberPw = sc.next();
	      
	      String code = service.createSecurityCode();
	      System.out.printf("보안문자 입력[%s] : ", code);
	      String input = sc.next();
	      
	      if(!input.equals(code)) {
	    	  System.out.println("보안문자가 일치하지 않습니다.");
	    	  return false;
	      }
	      
	      while(true) {
	    	  System.out.println("정말 탈퇴 하시겠습니까? (Y/N) :");
	    	  char check = sc.next().toUpperCase().charAt(0);
	    	  
	    	  if(check == 'N') {
	    		  System.out.println("\n=== 탈퇴 취소 ===\n");
	    		  return false; // 메서드 종료
	    	  }
	    	  
	    	  if(check == 'Y') {
	    		  break; //반복문 종료
	    	  }
	    	  
	    	  //'Y' , 'N' 이 아닌 경우
	    	  System.out.println("\n*** 잘못 입력하셨습니다 ***\n");
	      }
	      
	      try {
	    	  //회원 탈퇴 서비스 호출
	    	  int result = service.unRegisterMember(memberPw, Session.loginMember.getMemberNo());
	    	  
	    	  if(result > 0) {
	    		  System.out.println("\n=== 탈퇴 되었습니다 ===\n");
	    		  
	    		  //로그아웃
	    		  Session.loginMember = null;
	    		  
	    		  return true;
	    		  
	    	  } else {
	    		  System.out.println("\n**** 현재 비밀번호가 일치하지 않습니다. ****\n");
	    	  }
	    	   
	    	  
	      }catch(Exception e) {
	    	  System.out.println("\n*** 회원 탈퇴 중 예외 발생 ***\n");
	    	  e.printStackTrace();
	      }

	      return false;
	      
	   }



	private void updateMember() throws Exception{
		System.out.println("이름을 뭐로 바꾸시겠어요?");
		String name = sc.next();
		String gender = null;
		
		while(true) {
			
			System.out.println("성별을 뭐로 바꾸시겠어요?");
			gender = sc.next().toUpperCase();
			
			if(gender.equals("M") || gender.equals("F")) {
				break;
			} else {
				System.out.println("M/F 중 하나를 입력해주세요");
			}
			
		}

		int result = service.updateMember(name, gender, Session.loginMember.getMemberNo());
		
		if(result > 0) {
			Session.loginMember.setMemberName(name);
			Session.loginMember.setMemberGender(gender);
		}		
	}


	/** 회원 목록 조회
	 * 
	 */
	private void selectMemberList() throws Exception{
		// 2. 회원 목록 조회(아이디, 이름, 성별)"
		System.out.println("\n====회원 목록 조회====\n");
		
		List<Member> list = service.selectMemberList();
		
		for(Member member : list) {
			System.out.println(member.toString());
		}
		
	}

	/**
	 * 내 정보 조회
	 */
	public void selectMyInfo() {
		System.out.println("\n=== 내 정보 조회 ===\n");
		// 회원 번호, 아이디, 이름, 성별(남/여), 가입일
		// Session.loginMember 이용
		
		System.out.println("회원 번호 : " + Session.loginMember.getMemberNo());
		System.out.println("아이디 : " + Session.loginMember.getMemberId());
		System.out.println("이름 : " + Session.loginMember.getMemberName());
		
		if(Session.loginMember.getMemberGender().equals("M")) {
			System.out.println("성별 : 남");
		} else {
			System.out.println("성별 : 여");
		}
		
		System.out.println("가입일 :" + Session.loginMember.getEnrollDate());
		
	}
	

}
