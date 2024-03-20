package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Scanner;

public class CreateXMLFile {
	
	public static void main(String[] args) {
		
		try {
			Scanner sc = new Scanner(System.in);
			Properties prop = new Properties();
			
			System.out.print("생성할 파일 이름 : ");
			String fileName = sc.next();
			
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, fileName + ".xml file!!!"); // comment로 나오더라?
			
			System.out.println(fileName + ".xml 파일 생성 완료"); //콘솔에 찍은거야 의미없음
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
