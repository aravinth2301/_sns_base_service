package lk.inova.dao;

import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.RolePermission;
import lk.inova.exception.DAOException;

public interface RoleDao {

	Role createRole(Role role) throws DAOException;

	RolePermission createRolePermission(RolePermission rolePermission) throws DAOException;
	
	Role readRoleByCode(String roleCode) throws DAOException;

	Role updateRole(Role role) throws DAOException;
	
	RolePermission updateRolePermission(RolePermission rolePermission) throws DAOException;
}
