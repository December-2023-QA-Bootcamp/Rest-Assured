package unit;

import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;

public class GetTest {

	@Test
	public void baseGetTest() {
		RestAssured
		.given()
		.baseUri("http://localhost:3000")
		.log().all()
		.when()
		.get("/employees")
		.then()
		.log().all()
		.statusCode(200)
		.body("id[0]", equalTo(1))
		.body("firstName[1]", equalToIgnoringCase("sakib"));
	}
}
