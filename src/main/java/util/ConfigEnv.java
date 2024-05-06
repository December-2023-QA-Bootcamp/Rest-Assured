package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigEnv {

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private Properties properties;
	private String fileName;
	
	public ConfigEnv(String fileName) {
		this.fileName = fileName;
		initProperties();
	}
	
	private void initProperties() {
		try {
			properties = new Properties();
			InputStream inputStream = ConfigEnv.class.getClassLoader().getResourceAsStream(fileName);
			properties.load(inputStream);
		}
		catch (IOException e) 
		{
			LOGGER.info(fileName + " - FILE NOT FOUND");
		}
	}
	
	public String get(String key) 
	{
		return properties.getProperty(key);
	}
}
