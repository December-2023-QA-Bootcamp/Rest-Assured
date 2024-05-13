package util;

public interface IEndpoint {

	String EMPLOYEE_ENDPOINT 				= "/employees";
	String GET_EMPLOYEE_LASTNAME_ENDPOINT 	= "/employees/search/{lastName}";
	String DELETE_EMPLOYEE					= "/employees/{id}";
}
