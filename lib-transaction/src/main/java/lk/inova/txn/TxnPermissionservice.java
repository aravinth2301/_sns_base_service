package lk.inova.txn;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import lk.inova.dto.entity.Permission;
import lk.inova.exception.DAOException;

public interface TxnPermissionservice {

	
	void addPermissionWithTxn(Permission permission) throws DAOException,DataIntegrityViolationException;
	
	void editPermissionWithTxn(Permission permission) throws DAOException,DataIntegrityViolationException;
	
	List<Permission> readPermission(Permission permission) throws DAOException,DataIntegrityViolationException;
}
