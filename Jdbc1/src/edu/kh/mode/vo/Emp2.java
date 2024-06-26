package edu.kh.mode.vo;

public class Emp2 {

	private String empName;
	private String jobName;
	private int salary;
	private int annualSalary;
	private String hireDate;
	private char gender;
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(int annualSalary) {
		this.annualSalary = annualSalary;
	}
	
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public Emp2(String empName, String jobName, int salary, int annualSalary) {
		super();
		this.empName = empName;
		this.jobName = jobName;
		this.salary = salary;
		this.annualSalary = annualSalary;
	}
	
	public Emp2() {}
	@Override
	public String toString() {
		return empName + " / " + hireDate + " / " + gender;
	}
	
	
	
}
