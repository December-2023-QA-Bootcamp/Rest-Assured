package api;

import base.APIBaseStep;
import util.IEndpoint;

public class GetEmployeeStep extends APIBaseStep{
	
	public void setEndpoint() {
		setEndpoint(IEndpoint.EMPLOYEE_ENDPOINT);
	}
	
}
