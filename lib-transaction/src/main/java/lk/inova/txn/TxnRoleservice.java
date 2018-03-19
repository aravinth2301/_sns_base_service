package lk.inova.txn;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.RolePermission;
import lk.inova.exception.DAOException;

public interface TxnRoleservice {

	
	List<RolePermission>  addRoleWithTxn(Role role,List<RolePermission> listRolePermission) throws DAOException,DataIntegrityViolationException;
	
	List<RolePermission>  editRoleWithTxn(Role role,List<RolePermission> listRolePermission) throws DAOException,DataIntegrityViolationException;


}
