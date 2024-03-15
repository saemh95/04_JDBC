package edu.kh.emp.view;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.vo.Employee;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.sql.Connection;
import static edu.kh.emp.common.JDBCTemplate.*;
public class EmployeeView {
	
	private EmployeeService service = new EmployeeService();
	
	private Scanner sc = new Scanner(System.in);

	public void displayMenu() {
		
		int input = 0;
		
		do{
			try {
				System.out.println("<<<<<<<<<< Employee Management >>>>>>>>>>");
				System.out.println("1. Show All Employee");
				System.out.println("2. Add New Employee");
				System.out.println("3. Find Employee");
				System.out.println("4. Update Employee");
				System.out.println("5. Delete Employee");
				System.out.println("6. Find Employee by Department Code");
//				selectDeptEmp()
				System.out.println("7. Find Employee With Salary Higher then Inserted");
//				selectSalEmp()
				System.out.println("8. Department Sum of Employee Salary");
//				selectDeptTotalSal()
//				HashMap<String, Integer> string = D1 / integer = sum
				System.out.println("9. Find Employee by SSN");
//				selectEmpNO()
				System.out.println("10. Average Sum of Salary by Job Title");
//				selectJobAvgSalary() 
//				HasMap<String, Double>
//				대표 : 8000000.0 원
				System.out.println("0. Exit");
				
				System.out.print("Select Menu >>> ");
				input = sc.nextInt();
				
				switch (input) {
				case 1 : selectAll();
					break;
				case 2 : insertEmp();
					break;
				case 3 : findEmp();
					break;
				case 4 : updateEmp();
					break;
				case 5 : deleteEmp();
					break;
				case 6 : selectDeptEmp();
					break;
				case 7 : selectSalEmp();
					break;
				case 8 : selectDeptTotalSal();
					break;
				case 9 : selectEmpNO();
					break;
				case 10 : selectJobAvgSalary();
					break;
				case 0 : System.out.println("<<<<<<<<<< Exit >>>>>>>>>>>");
					break;
				default : System.out.println("Select Numbers Only From 0~5");
				}
				
				
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("Only Numbers");
				input = -1;
				sc.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while (input!=0);
		
	}

	public void selectAll() throws Exception{
		
		
		System.out.println("<<<<<<<<<< 1. Show All Employee >>>>>>>>>>");
		
		
		List <Employee> empList = service.selectAll(); 
		printAll(empList);
	}

	public void insertEmp() throws Exception{
		
		System.out.println("<<<<<<<<<< 2. Add New Employee >>>>>>>>>>");
		int empId = inputEmpId();
		
		System.out.print("Name >> ");
		String empName = sc.next();
		System.out.print("Social Security Number >> ");
		String empNo = sc.next();
		System.out.print("Email >> ");
		String email = sc.next();
		System.out.print("Phone Number >> ");
		String phone = sc.next();
		System.out.print("Department Code(D1~D9) >> ");
		String deptCode = sc.next();
		System.out.print("Job Code(J1~J7) >> ");
		String jobCode = sc.next();
		System.out.print("Salary Level(S1~S6) >> ");
		String salLvl = sc.next();
		System.out.print("Salary >> ");
		int salary = sc.nextInt();
		System.out.print("Bonus >> ");
		double bonus = sc.nextDouble();
		System.out.print("Manager ID >> ");
		int managerId = sc.nextInt();
		
		Employee emp = new Employee(empId, empName, empNo, email, phone, salary, deptCode, jobCode, salLvl, bonus, managerId);
		
		int result = service.insertEmp(emp);
		
		if (result > 0) System.out.println("Insert Completed");
		else System.out.println("Insert Error");
	}

	public int inputEmpId() {
		System.out.print("insert ID >> ");
		int empId = sc.nextInt();
		sc.nextLine();
		return empId;
	}
	public void findEmp() throws Exception{
		System.out.println("<<<<<<<<<< 3. Find Employee >>>>>>>>>>");
		
		int empId = inputEmpId();
		
		Employee emp = service.findEmp(empId);
		
		printOne(emp);
		
	}

	public void updateEmp() throws Exception{
		System.out.println("<<<<<<<<<< 4. Update Employee >>>>>>>>>>");
		
		int empId = inputEmpId();
		System.out.print("Email >> ");
		String email = sc.next();		
		System.out.print("Phone Number >> ");
		String phone = sc.next();
		System.out.print("Salary >> ");
		int salary = sc.nextInt();
		
		Employee emp = new Employee();
		
		emp.setEmpId(empId);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setSalary(salary);
		
		int result = service.updateEmp(emp);
		
		if (result > 0 ) System.out.println("Update confirmed");
		else System.out.println("Employee does not match");
		
	}

	public void deleteEmp() throws Exception{
		System.out.println("<<<<<<<<<< 5. Delete Employee >>>>>>>>>>");
		
		int empId= inputEmpId();
		
		System.out.print("Confirm Delete (Y/N) >> ");
		char input = sc.next().toUpperCase().charAt(0);
		
		if (input == 'Y') {
			int result = service.deleteEmp(empId);
			
			if(result > 0) System.out.println("Deleted");
			else System.out.println("Employee number not mached");
			
		} else System.out.println("Delete canceled");
		
	}
	
	public void selectDeptEmp() throws Exception{
		
		System.out.println("<<<<<<<<<< 6. Find Employee by Department Code >>>>>>>>>>");
		
		System.out.print("Insert Code >> ");
		String input = sc.next();
		
		List <Employee> empList = service.selectDeptEmp(input); 
		if (empList.isEmpty()) System.out.println("No Employee Found");
		printAll(empList);
		
	}
	
	public void selectSalEmp() throws Exception{
		
		
		System.out.println("<<<<<<<<<< 7. Find Employee With Salary Higher then Inserted >>>>>>>>>>");
		System.out.print("Insert Salary >> ");
		int input = sc.nextInt();
		
		List <Employee> empList = service.selectSalEmp(input); 
		if (empList.isEmpty()) System.out.println("No Employee Found");
		printAll(empList);
		
	}
	
	public void selectDeptTotalSal() throws Exception{
		
		System.out.println("<<<<<<<<<< 8. Department Sum Salary >>>>>>>>>>");
		
		List<Map<String, Integer>> empList = service.selectDeptTotalSal();
		printDeptTotalSal(empList);
	}
	
	public void selectEmpNO() throws Exception{
		
		System.out.println("<<<<<<<<<< 9. Find Employee by SSN >>>>>>>>>>");
		
		System.out.print("Insert SSN >> ");
		String input = sc.next();
		
		Employee emp = service.selectEmpNo(input);
		printOne(emp);
	}
	
	public void selectJobAvgSalary() throws Exception{
		
		System.out.println("<<<<<<<<<< 10. Average Sum of Salary by Job Title >>>>>>>>>>");
		
		Map<String, Double> empList = service.selectJobAvgSalary();
		
		
		printJobAvgSal(empList);
	}
	

	// 보조 메서드
	
	/** 전달받은 사원 List 모두 출력
	 *
	 */
	public void printAll(List<Employee> empList) {
		
		if(empList.isEmpty()) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번  |   이름  | 주민 등록 번호 |        이메일        |  전화 번호  |  부서  | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(Employee emp : empList) {
				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
						emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(),
						emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
			}
		
		}
		
		return;
	}
	/** 사원 1명 정보 출력
	 * @param emp
	 */
	public void printOne(Employee emp) {
		if(emp == null) {
			System.out.println("조회된 사원 정보가 없습니다.");
			
		} else {
			System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
			System.out.println("------------------------------------------------------------------------------------------------");
			
			System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
					emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(),
					emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
		}
	}
	
	public void printDeptTotalSal(List<Map<String, Integer>> empList) {
		
		if (empList.isEmpty()) System.out.println("조회된 사원 정보가 없습니다.");
		System.out.println("Department Code | Total Salary");
	    for (Map<String, Integer> deptSal : empList) {
	        for (Map.Entry<String, Integer> entry : deptSal.entrySet()) {
	            System.out.printf(" %10s     |   %10d \n", entry.getKey(), entry.getValue());
	        }
	    }
	}

	public void printJobAvgSal(Map<String, Double> map) {
		if (map.isEmpty()) System.out.println("조회된 사원 정보가 없습니다.");
		System.out.println("Job Name |  Average Salary");
		for (Map.Entry<String, Double> avgSal : map.entrySet()) {
			System.out.printf(" %3s   |  %.1f\n", avgSal.getKey(), avgSal.getValue());
		}
	}
}
