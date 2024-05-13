package unit;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import dto.Employee;
import dto.Employee.Department;
import dto.EmployeeDetails;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.ConfigEnv;
import util.EnvProperties;

public class PostTest {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	EnvProperties envConstant = EnvProperties.LOCAL_ENV;
	ConfigEnv env = new ConfigEnv(envConstant.getFileName());
	
	//@Test
	public void stringBodyPostTest() {
		String url = env.get(envConstant.getKey());
		String body = "{\n"
				+ "    \"firstName\": \"Alif\",\n"
				+ "    \"lastName\": \"Hamza\",\n"
				+ "    \"dob\": \"1998-12-22\"\n"
				+ "}";
		
		RestAssured
			.given()
			.baseUri(url)
			.body(body)
			.contentType(ContentType.JSON)
			.log().all()
			.when()
			.post("/employees")
			.then()
			.log().all()
			.statusCode(201);
	}
	
	@Test
	public void objectPostTest() {
		String url = env.get(envConstant.getKey());
		
		Employee employee = new Employee("Musfiq", "Rahim", "1998-12-03");
		Department[] departments = {Department.DELIVERY, Department.PRODUCTION, Department.HR};
		employee.setDepartment(departments);
		
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails.setEmployee(employee);
		
		Response response = RestAssured
		.given()
		.baseUri(url)
		.body(employeeDetails)
		.contentType(ContentType.JSON)
		.log().all()
		.when()
		.post("/employees")
		.then()
		.log().all()
		.statusCode(201)
		.and().extract().response();
		
		Employee responseEmployee = response.as(Employee.class);
		
		LOGGER.info(responseEmployee.getFullName());
	}
}
