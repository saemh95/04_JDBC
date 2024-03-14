package edu.kh.jdbc.run;

import java.util.*;
import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class JDBCRun3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		TestService service = new TestService();
		
		try {
			
			System.out.print("입력 : ");
			int testNo = sc.nextInt();
			sc.nextLine();	
			System.out.print("제목 : ");
			String testTitle = sc.nextLine();
			
			System.out.print("내용 : ");
			String testContent = sc.nextLine();
			
			TestVO vo = new TestVO(testNo, testTitle, testContent);
			
			int result = service.update(vo);
			
			if(result > 0) System.out.println("update complete");
			else System.out.println("error");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
