package edu.kh.emp.common;

import java.util.*;
import java.io.*;
import java.sql.*;

public class CreateXMLFile {

	public static void main(String[] args) {
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			Properties prop = new Properties();
			
			System.out.print("XML file name : ");
			String fileName = sc.next();
			
			FileOutputStream fos = new FileOutputStream(fileName + ".xml");
			
			prop.storeToXML(fos, fileName + ".xml");
			
			System.out.println(fileName + ".xml file created");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
