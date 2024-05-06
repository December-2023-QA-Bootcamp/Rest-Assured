package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDetails {

	@JsonProperty("EmployeeDesc")
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
