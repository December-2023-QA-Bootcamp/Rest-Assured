package util;

public enum Constant {

	LOCAL_ENV("baseUrl","env-local.properties"),
	DEV_ENV("baseUrl","env-dev.properties"),
	PROD_ENV("baseUrl","env-prod.properties");
	
	private String key;
	private String value;
	
	private Constant(String key, String value) {
		this.key 	= key;
		this.value 	= value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
}
