package lk.inova.txn.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lk.inova.dao.PermissionDao;
import lk.inova.dto.entity.Permission;
import lk.inova.exception.DAOException;
import lk.inova.txn.TxnPermissionservice;

@Repository
public class TxnPermissionserviceImpl implements TxnPermissionservice{

	@Autowired
	private PermissionDao permissionDao;

	@Override
	@Transactional
	public void addPermissionWithTxn(Permission permission) throws DAOException,DataIntegrityViolationException {
		permissionDao.createPermission(permission);
		
	}

	@Override
	@Transactional
	public void editPermissionWithTxn(Permission permission) throws DAOException ,DataIntegrityViolationException{
		permissionDao.updatePermission(permission);
		
	}

	@Override
	public List<Permission> readPermission(Permission permission) throws DAOException, DataIntegrityViolationException {
		return permissionDao.readPermissionByFilters(permission);
	}
	
	
	

	
}
