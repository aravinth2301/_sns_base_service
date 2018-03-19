package lk.inova.util;

public enum TBLRecordStatus {

	ACTIVE(1,"Active");
	
	private String description;
	private Integer id;

	private TBLRecordStatus(Integer id,String description) {
		this.id = id;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
