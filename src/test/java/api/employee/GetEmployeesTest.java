package api.employee;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import api.GetEmployeeStep;
import util.Constant;

public class GetEmployeesTest {

	static GetEmployeeStep getEmployeeStep;
	
	@BeforeAll
	public static void init() {
		Constant env = Constant.LOCAL_ENV;
		getEmployeeStep = new GetEmployeeStep();
		getEmployeeStep.build(env);
		getEmployeeStep.setEndpoint();
	}
	
	@Test
	@DisplayName("TC_563 : GET Employee Happy Path")
	public void test() {
		getEmployeeStep.getRequest();
		getEmployeeStep.getResponse();
	}
}
