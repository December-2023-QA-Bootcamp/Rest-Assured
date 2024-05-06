package misc;

import java.io.File;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import io.restassured.path.json.JsonPath;

public class JsonPathTest {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	File file = new File("/Users/mdnas/eclipse-workspace/rest.assured.dec.23/src/test/resources/Sample.json");
	
	@Test
	public void test() {
		LOGGER.info("File Exists - " + file.exists());
		
		JsonPath jsonPath = JsonPath.from(file);
		LOGGER.info(jsonPath.get("quiz.sport.q1.options[2]") + "");
	}
}
