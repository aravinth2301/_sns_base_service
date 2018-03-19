package lk.inova.txn.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lk.inova.dao.RoleDao;
import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.RolePermission;
import lk.inova.exception.DAOException;
import lk.inova.txn.TxnRoleservice;

@Repository
public class TxnRoleserviceImpl implements TxnRoleservice {

	@Autowired
	private RoleDao roleDao;

	@Override
	@Transactional
	public List<RolePermission> addRoleWithTxn(Role role, List<RolePermission> listRolePermission)
			throws DAOException, DataIntegrityViolationException {

		Role newRole = roleDao.createRole(role);

		if (listRolePermission == null || listRolePermission.isEmpty()) {
			throw new DAOException("400", "Permission Required");
		}

		for (int i = 0; i < listRolePermission.size(); i++) {
			listRolePermission.get(i).setRole(newRole);
			RolePermission tempRolePermission = listRolePermission.get(i);
			roleDao.createRolePermission(tempRolePermission);
		}

		return listRolePermission;

	}

	@Override
	@Transactional
	public List<RolePermission> editRoleWithTxn(Role role, List<RolePermission> listRolePermission)
			throws DAOException, DataIntegrityViolationException {
		roleDao.updateRole(role);
		return null;
	}

}
