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
import util.EnvProperties;

public abstract class APIBaseStep {

	private RequestSpecification requestSpec;
	private Response response;
	
	/**
	 * This method will initialize the RequestSpecification object {@link RequestSpecification}
	 * ConfigEnv will get the environment {@link ConfigEnv}
	 * @param constant {@link EnvProperties}
	 * Which environment will run Constant will get the URL
	 */
	public void build(EnvProperties constant) {
		ConfigEnv env = new ConfigEnv(constant.getFileName());
		
		requestSpec = RestAssured.given();
		setEnvironment(env.get(constant.getKey()));
	}
	
	private void setEnvironment(String url) {
		requestSpec.baseUri(url);
	}
	
	public void setEndpoint(String endpoint) {
		requestSpec.basePath(endpoint);
	}
	
	public void setPathParam(String param, Object paramValue) {
		requestSpec.pathParam(param, paramValue);
	}
	
	public void setQueryParam(String param, Object paramValue) {
		requestSpec.queryParam(param, paramValue);
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
		response = requestSpec.get();
		response.then().log().all();
		return response;
	}
	
	public Response postRequest() {
		requestSpec.log().all();
		response = requestSpec.post();
		response.then().log().all();
		return response;
	}
	
	public Response putRequest() {
		requestSpec.log().all();
		response = requestSpec.put();
		response.then().log().all();
		return response;
	}
	
	public Response deleteRequest() {
		requestSpec.log().all();
		response = requestSpec.delete();
		response.then().log().all();
		return response;
	}
	
	public Response getResponse() {
		return response;
	}
	
	public int getStatusCode() {
		return response.getStatusCode();
	}
}
