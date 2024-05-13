package api;

import base.APIBaseStep;
import util.IEndpoint;

public class DeleteEmployeeStep extends APIBaseStep{

	public void setEndpointByParam(int id) {
		setEndpoint(IEndpoint.DELETE_EMPLOYEE);
		setPathParam("id", id);
	}
}
