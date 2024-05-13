package api;

import base.APIBaseStep;
import util.IEndpoint;

public class GetEmployeeByLastNameStep extends APIBaseStep{

	public void setEndpointWithParam(String lastName) {
		setEndpoint(IEndpoint.GET_EMPLOYEE_LASTNAME_ENDPOINT);
		setPathParam("lastName", lastName);
	}
}
