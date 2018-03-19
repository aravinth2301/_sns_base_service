package lk.inova.dao;

import java.util.List;

import lk.inova.dto.entity.Permission;
import lk.inova.exception.DAOException;

public interface PermissionDao {

	void createPermission(Permission permission) throws DAOException;

	List<Permission> readPermissionByFilters(Permission permissionCode) throws DAOException;
	
	void updatePermission(Permission permission) throws DAOException;

}
