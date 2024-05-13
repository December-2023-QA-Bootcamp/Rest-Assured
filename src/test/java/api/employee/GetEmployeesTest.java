package api.employee;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import api.GetEmployeeStep;
import dto.Employee;
import io.restassured.response.Response;
import util.EnvProperties;

public class GetEmployeesTest {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	static GetEmployeeStep getEmployeeStep;
	
	@BeforeAll
	public static void init() {
		EnvProperties env = EnvProperties.LOCAL_ENV;
		getEmployeeStep = new GetEmployeeStep();
		getEmployeeStep.build(env);
		getEmployeeStep.setEndpoint();
	}
	
	//@TestFactory
	@DisplayName("TC_563 : GET Employee Happy Path")
	public List<DynamicNode> test() {
		
		List<DynamicNode> tests = new LinkedList<DynamicNode>();
		
		Response response = getEmployeeStep.getRequest();
		
		tests.add(DynamicTest.dynamicTest("Response Status Code Validation", 
				()-> Assertions.assertEquals(201, response.getStatusCode())));
		
		Employee [] employees = response.as(Employee[].class);
		
		LOGGER.info("Employee Size : " + employees.length);
		
		tests.add(DynamicTest.dynamicTest("Second Employee Full Name", 
				()-> Assertions.assertEquals("Sakib Al Hasan", employees[1].getFullName())));
		
		return tests;
	}
	
	
	@TestFactory
	@DisplayName("TC_564 : GET Employee Method Not Allowed")
	public List<DynamicNode> methodNotAllowed()
	{
		List<DynamicNode> tests = new LinkedList<DynamicNode>();
		
		Response response = getEmployeeStep.putRequest();
		
		tests.add(DynamicTest.dynamicTest("Response Status Code Validation", 
				()-> Assertions.assertEquals(409, response.getStatusCode())));
		
		tests.add(DynamicTest.dynamicTest("Response Status Line Validation", 
				()-> Assertions.assertEquals("Method Not Allowed", response.getStatusLine())));
		
		return tests;
	}
	
	
	/**
	 * Sample Test Case
	 * Get Employee by Last Name 
	 * 
	 * 1st - Happy Path - record exists
	 * 		200, Record returns
	 * 		api (actual) response vs db (expected) response
	 * 2nd - Happy Path - record not available
	 * 		200, no records . 404 Not Found
	 * 
	 * Negative Tests - 
	 * 3rd - param type mismatch
	 * 		400 Bad Request
	 * 4th - empty param ""
	 * 		400 Bad Request
	 * 5th - null/missing param (null)
	 * 		400 Bad Request
	 * 6th - invalid last name (x134!.)
	 * 		400 Bad Request / 404 Not Found
	 * 7th - different method - PUT
	 * 		409 Method Not Allowed
	 * 8th - extra header
	 * 		200 Success
	 * 9th - Invalid Header
	 * 10th - empty header
	 * 11th - invalid auth
	 * 12th - missing auth 
	 */
}
