package util;

public enum EnvProperties {

	LOCAL_ENV("baseUrl","env-local.properties"),
	DEV_ENV("baseUrl","env-dev.properties"),
	PROD_ENV("baseUrl","env-prod.properties");
	
	private String key;
	private String fileName;
	
	private EnvProperties(String key, String fileName) {
		this.key 		= key;
		this.fileName 	= fileName;
	}
	
	@Override
	public String toString() {
		return fileName;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getFileName() {
		return fileName;
	}
}
