package api.employee;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import static org.junit.jupiter.api.DynamicTest.*;
import org.junit.jupiter.api.TestFactory;

import api.DeleteEmployeeStep;
import api.GetEmployeeByLastNameStep;
import api.PostEmployeeStep;
import dto.Employee;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.EnvProperties;

public class PostEmployeeTest {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	static PostEmployeeStep postEmployeeStep;
	static EnvProperties env;

	@BeforeAll
	public static void init() {
		env = EnvProperties.LOCAL_ENV;

		postEmployeeStep = new PostEmployeeStep();
		postEmployeeStep.build(env);
		postEmployeeStep.setEndpoint();
	}

	/*
	 * 
	 * ALIF HAMZA Created boolean exists = GET Employee ALIF HAMZA exists? DELETE
	 * ALIF HAMZA
	 * 
	 * POST ALIF HAMZA 201
	 */

	@TestFactory
	@DisplayName("TC_543 : POST Employee Happy Path")
	public List<DynamicNode> test() 
	{
		List<DynamicNode> tests = new LinkedList<DynamicNode>();

		try 
		{
			Employee employee = new Employee("Alif", "Hamzaa", "1998-12-22");

			List<DynamicNode> getTest = new LinkedList<DynamicNode>();
			
			GetEmployeeByLastNameStep getEmployeeStep = new GetEmployeeByLastNameStep();
			getEmployeeStep.build(env);
			getEmployeeStep.setEndpointWithParam(employee.getLastName());

			Response getResponse = getEmployeeStep.getRequest();

			int getEmployeeByLatNameStatusCode = getResponse.getStatusCode();

			if (getEmployeeByLatNameStatusCode != 404) {
				getTest.add(dynamicTest("GET Employee Status Code Validation",
						() -> assertEquals(200, getEmployeeByLatNameStatusCode)));

				Employee[] existingEmployees = getResponse.as(Employee[].class);

				boolean isEmployeeExists = false;
				int id = -1;

				for (int i = 0; i < existingEmployees.length; i++) {
					if (employee.getFirstName().equalsIgnoreCase(existingEmployees[i].getFirstName())) {
						if (employee.getDob().equalsIgnoreCase(existingEmployees[i].getDob())) {
							isEmployeeExists = true;
							id = existingEmployees[i].getId();
						}
					}
				}

				LOGGER.info(String.format("Employee Exists ? [%s : %s]", isEmployeeExists, id));

				tests.add(DynamicContainer.dynamicContainer("GET Employee By Last Name", getTest));
				
				List<DynamicNode> deleteTest = new LinkedList<DynamicNode>();
				
				// DELETE if exists
				if (isEmployeeExists) {
					DeleteEmployeeStep deleteEmployeeStep = new DeleteEmployeeStep();
					deleteEmployeeStep.build(env);

					if (id != -1) {
						deleteEmployeeStep.setEndpointByParam(id);
						Response deleteResponse = deleteEmployeeStep.deleteRequest();

						int delStatusCode = deleteResponse.getStatusCode();
						deleteTest.add(
								dynamicTest(String.format("DELETE Employee Status Code Validation [%s]", delStatusCode),
										() -> assertEquals(200, delStatusCode)));

						tests.add(DynamicContainer.dynamicContainer(String.format("DELETE Employee [%s]", id), deleteTest));
						
						if (delStatusCode != 200) {
							throw new Exception(String.format("Cannot Delete employee [%s] by id [%s]",
									employee.getFullName(), id));
						}
					} else {
						throw new Exception("ID Not Found : " + employee.getFullName());
					}

				}
			}
			
			List<DynamicNode> postTest = new LinkedList<DynamicNode>();
			
			// POST Employee Body setup
			postEmployeeStep.setBody(employee);
			postEmployeeStep.setContentType(ContentType.JSON);

			// POST Request
			Response response = postEmployeeStep.postRequest();

			postTest.add(
					dynamicTest("Response Status Code Validation", () -> assertEquals(201, response.getStatusCode())));

			Employee actualEmployee = response.as(Employee.class);

			postTest.add(dynamicTest("First Name Validation ["+actualEmployee.getFirstName()+"]",
					() -> assertEquals(employee.getFirstName(), actualEmployee.getFirstName())));

			postTest.add(dynamicTest("Last Name Validation ["+actualEmployee.getLastName()+"]",
					() -> assertEquals(employee.getLastName(), actualEmployee.getLastName())));

			postTest.add(dynamicTest("DOB Validation ["+actualEmployee.getDob()+"]",
					() -> assertEquals(employee.getDob(), actualEmployee.getDob() + 1)));

			postTest.add(dynamicTest("Full Name Validation ["+actualEmployee.getFullName()+"]",
					() -> assertEquals(employee.getFirstName() + " " + employee.getLastName(),
							actualEmployee.getFullName())));

			LOGGER.info(actualEmployee.getAge());

			tests.add(DynamicContainer.dynamicContainer("POST Employee", postTest));
			
		} catch (Exception e) {
			tests.add(dynamicTest("FAILED : " + e.getCause(), () -> e.getStackTrace()));
		}

		return tests;
	}
	
	
	/**
	 * Sample Tests -
	 * 1st - Happy Path
	 * 		201, 
	 * 		API (actual) response vs DB (expected) response
	 * 
	 * Negative Tests - 
	 * 2nd - empty body
	 * 		400 Bad Request
	 * 3rd - body null
	 * 		400 Bad Request
	 * 4th - required fileds missing
	 * 		400 Bad Request
	 * 5th - firstName missing
	 * 		400 Bad Request
	 * 6th - lastName missing
	 * 		400 Bad Request / 404 Not Found
	 * 7th - different method - PUT
	 * 		409 Method Not Allowed
	 * 8th - extra header
	 * 		200 Success
	 * 9th - Invalid Header
	 * 10th - empty header
	 * 11th - invalid auth
	 * 12th - missing auth 
	 * 13th - firstName type mismatch (int )
	 * 14th - lastName type mismatch (int )
	 * 15th - dob type mismatch (int )
	 * 16th - firstName missing
	 * 17th - lastName missing
	 * 18th - dob type missing
	 */
}
