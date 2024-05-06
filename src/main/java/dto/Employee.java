package dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

	private Integer id;
	private String firstName;
	private String lastName;
	private String dob;
	private String fullName;
	private String age;
	
	private Department[] department;
	
	/**
	 * Custom Constructor for POST Request Object
	 * @param firstName
	 * @param lastName
	 * @param dob
	 */
	public Employee(String firstName, String lastName, String dob) {
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.dob 		= dob;
	}
	
	public Employee() {}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public Department[] getDepartment() {
		return department;
	}
	
	public void setDepartment(Department[] department) {
		this.department = department;
	}
	
	public enum Department{
		IT, HR, SALES, SECURITY, DELIVERY, LOGISTIC, PRODUCTION
	}
}
