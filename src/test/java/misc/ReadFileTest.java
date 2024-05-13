package misc;

import java.io.File;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class ReadFileTest {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Test
	public void test() {
		File file = new File("src/test/resources/Sample.json");
		LOGGER.info("File Exists? " + file.exists());
	}
}
