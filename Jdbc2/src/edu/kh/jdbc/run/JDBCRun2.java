package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class JDBCRun2 {

	public static void main(String[] args) {
		
		TestService service = new TestService();
		
		TestVO vo1 = new TestVO(70, "Title70", "Content70");
		TestVO vo2 = new TestVO(80, "Title80", "Content80");
		TestVO vo3 = new TestVO(90, "Title90", "Content90");

		try {
			
			int result = service.insert(vo1, vo2, vo3);
	
			if (result > 0 ) System.out.println("confirmed");
			else System.out.println("erorr");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
