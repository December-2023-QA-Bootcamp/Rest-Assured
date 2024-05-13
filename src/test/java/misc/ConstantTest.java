package misc;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import util.EnvProperties;

public class ConstantTest {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Test
	public void test() {
		String filedName 	= EnvProperties.LOCAL_ENV.name();
		String value		= EnvProperties.LOCAL_ENV.toString();
		
		LOGGER.info("Filed Name 	: " + filedName);
		LOGGER.info("Filed Value 	: " + value);
	}
}
