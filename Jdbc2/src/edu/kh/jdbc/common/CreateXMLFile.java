package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.util.*;
public class CreateXMLFile {

//	XML (extensible markup language)
	
	public static void main(String[] args) {
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			Properties prop = new Properties();
			
			System.out.print("생성할 파일 이름 : ");
			String fileName = sc.next();
			
			FileOutputStream fos = new FileOutputStream(fileName+".xml");
			
			prop.storeToXML(fos, fileName + ".xml");
			
			System.out.println(fileName + ".xml 파일 생성 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
