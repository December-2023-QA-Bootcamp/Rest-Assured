package base;

import java.io.File;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.ConfigEnv;
import util.Constant;

public abstract class APIBaseStep {

	private RequestSpecification requestSpec;
	private Response response;
	
	public void build(Constant constant) {
		ConfigEnv env = new ConfigEnv(constant.getValue());
		
		requestSpec = RestAssured.given();
		setEnvironment(env.get(constant.getKey()));
	}
	
	private void setEnvironment(String url) {
		requestSpec.baseUri(url);
	}
	
	public void setEndpoint(String endpoint) {
		requestSpec.basePath(endpoint);
	}
	
	public void setContentType(ContentType contentType) {
		requestSpec.contentType(contentType);
	}
	
	public void setBody(String body) {
		requestSpec.body(body);
	}
	
	public void setBody(File body) {
		requestSpec.body(body);
	}
	
	public void setBody(Map<String,Object> body) {
		requestSpec.body(body);
	}
	
	public void setBody(Object body) {
		requestSpec.body(body);
	}
	
	public void setHeaders(Headers headers) {
		requestSpec.headers(headers);
	}
	
	public void setHeader(Header header) {
		requestSpec.header(header);
	}
	
	public Response getRequest() {
		requestSpec.log().all();
		return response = requestSpec.get();
	}
	
	public Response postRequest() {
		requestSpec.log().all();
		return response = requestSpec.post();
	}
	
	public Response putRequest() {
		requestSpec.log().all();
		return response = requestSpec.put();
	}
	
	public Response deleteRequest() {
		requestSpec.log().all();
		return response = requestSpec.delete();
	}
	
	public Response getResponse() {
		response.then().log().all();
		return response;
	}
}
