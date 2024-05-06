package misc;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import util.Constant;

public class ConstantTest {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Test
	public void test() {
		String filedName 	= Constant.LOCAL_ENV.name();
		String value		= Constant.LOCAL_ENV.toString();
		
		LOGGER.info("Filed Name 	: " + filedName);
		LOGGER.info("Filed Value 	: " + value);
	}
}
