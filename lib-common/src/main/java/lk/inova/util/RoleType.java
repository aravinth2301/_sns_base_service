package lk.inova.util;

public enum RoleType {

	ADMIN(1,"System"),
	ZONAL(2,"Zonal");
	
	
	private Integer id;
	private String description;
	private RoleType(Integer id,String description) {
		
		this.id = id;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
