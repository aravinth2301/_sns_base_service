package lk.inova.dto;

import java.io.Serializable;

public class WFUserDTO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5466339122705456397L;

	private WFRoleDTO role;
	
	private WFZoneDTO zone;
	

	public WFRoleDTO getRole() {
		return role;
	}

	public void setRole(WFRoleDTO role) {
		this.role = role;
	}

	public WFZoneDTO getZone() {
		return zone;
	}

	public void setZone(WFZoneDTO zone) {
		this.zone = zone;
	}
	
	
	
}
