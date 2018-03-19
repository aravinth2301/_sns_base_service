package lk.inova.sbeans;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import lk.inova.dto.entity.Role;

@Component
@ConfigurationProperties("metadata") 
public class StartUpConfigProperty {

	
	private List<Role> listMetaZoneRoles;

	private List<TempRoleFunctionDTO> listMetaZoneRolesPermission;
	
	private Role roleAdmin;
	
	public List<Role> getListMetaZoneRoles() {
		return listMetaZoneRoles;
	}

	public void setListMetaZoneRoles(List<Role> listMetaZoneRoles) {
		this.listMetaZoneRoles = listMetaZoneRoles;
	}

	public Role getRoleAdmin() {
		return roleAdmin;
	}

	public void setRoleAdmin(Role roleAdmin) {
		this.roleAdmin = roleAdmin;
	}

	public List<TempRoleFunctionDTO> getListMetaZoneRolesPermission() {
		return listMetaZoneRolesPermission;
	}

	public void setListMetaZoneRolesPermission(List<TempRoleFunctionDTO> listMetaZoneRolesPermission) {
		this.listMetaZoneRolesPermission = listMetaZoneRolesPermission;
	}

	
	
	
}
