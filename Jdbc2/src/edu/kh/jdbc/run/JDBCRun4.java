package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import java.util.*;
public class JDBCRun4 {

	public static void main(String[] args) {
		
		TestService service = new TestService();
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.print("삭제할 번호 입력 : ");
			int input = sc.nextInt();		
			
			int result = service.remove(input);
			
			if(result > 0) System.out.println("Delete complete");
			else System.out.println("Wrong number");
			
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
			
		}
	}
	
}
