package lk.inova.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lk.inova.dto.entity.Permission;
import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.RolePermission;
import lk.inova.dto.entity.User;
import lk.inova.util.JsonUtil;

@JsonInclude(Include.NON_NULL)
public class RequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7766283414388646551L;

	private Permission permission;

	private List<RolePermission> listRolePermission;
	
	private Role role;

	private User user;
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<RolePermission> getListRolePermission() {
		return listRolePermission;
	}

	public void setListRolePermission(List<RolePermission> listRolePermission) {
		this.listRolePermission = listRolePermission;
	}

	
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return JsonUtil.parseToString(this);
	}
}
