package edu.kh.jdbc.run;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class JDBCRun {

	public static void main(String[] args) {
		
		TestService service = new TestService();
		
		TestVO vo1 = new TestVO(1, "Title01", "Content01");
		
		try {
			int result = service.insert(vo1);
			
			if (result > 0) System.out.println("intert confirmed");
			else System.out.println("insert error");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
