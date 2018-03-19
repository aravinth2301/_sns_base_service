package lk.inova.util;

public enum Variables {

	ZONE("zone"),
	TASK_STATUS("taskStatus");
	private String key;

	private Variables(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
}
