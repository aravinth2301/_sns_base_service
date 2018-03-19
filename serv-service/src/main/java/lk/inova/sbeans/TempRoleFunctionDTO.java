package lk.inova.sbeans;

import lk.inova.dto.entity.Role;

import java.util.List;
import lk.inova.dto.entity.Permission;
public class TempRoleFunctionDTO {

	private Role role;
	private List<Permission> listPermission;
		
	public TempRoleFunctionDTO() {
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Permission> getListPermission() {
		return listPermission;
	}
	public void setListPermission(List<Permission> listPermission) {
		this.listPermission = listPermission;
	}
	
	
	
	
	
	
}