package study.spring.emp.hr.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmpVO {

	@Min(value = 300)
	private int employeeId;
	
	//^ 첫글자 , 자바에서는 빼고다
	@Pattern(regexp="^[A-Z가-힣]+[a-zA-Z가-힣]+$")
	@Size(max = 10 , message = "10글자 이하로 입력")
	private String firstName;
	
	@Pattern(regexp = "[\\w가-힣]+$")
	@Size(max = 12 , message = "12글자 이하로 입력")
	private String lastName;
	
	//|| << 이거 두번은 . 을 의미함
	@Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+\\..*$")
	private String email;
	
	// | 는 or  , \\s 는 공백 , {3,4} 3~4글자 , {4 , } 는 4이상 , \\d 숫자만 들어올수있음
	@Pattern(regexp = "^[0][1][0-9](-|\\s)\\d{3,4}(-|\\s)\\d{4}$")
	private String phoneNumber;
	
	//Past 오늘의 끝까지 즉 오늘 이상 날짜가 들어오면 오류 메세지 보냄
	@Past
	private java.sql.Date hireDate;
	private String jobId;
	
	@Digits(integer=6, fraction=2)
	private double salary;
	
	@DecimalMax(value = "0.99")
	private double commissionPct;
	
	private Object managerId;
	private Object departmentId;
	//테이블 컬럼 이랑 같이 순서가 같이 선언함

	@Override
	public String toString() {
		return "EmpVO [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", jobId=" + jobId + ", salary="
				+ salary + ", commissionPct=" + commissionPct + ", managerId=" + managerId + ", departmentId="
				+ departmentId + "]";
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(double commissionPct) {
		this.commissionPct = commissionPct;
	}

	public Object getManagerId() {
		return managerId;
	}

	public void setManagerId(Object managerId) {
		this.managerId = managerId;
	}

	public Object getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Object departmentId) {
		this.departmentId = departmentId;
	}

}
