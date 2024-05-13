package api;

import base.APIBaseStep;
import util.IEndpoint;

public class PostEmployeeStep extends APIBaseStep{

	public void setEndpoint() {
		setEndpoint(IEndpoint.EMPLOYEE_ENDPOINT);
	}
}
