package lk.inova.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lk.inova.dao.RoleDao;
import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.RolePermission;
import lk.inova.exception.DAOException;
import lk.inova.util.ConnectionDao;

@Repository
public class RoleDaoImpl extends ConnectionDao implements RoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4088966899838829454L;

	@Override
	public Role createRole(Role role) throws DAOException {
		return this.persistWiTxnManager(role, Role.class);

	}

	@Override
	public Role readRoleByCode(String roleCode) throws DAOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("rcode", roleCode);
		List<Role> listUser = this.excuteQuery("select o from Role o where o.code=:rcode", params,
				Role.class);
		if (listUser == null && listUser.isEmpty())
			throw new DAOException("404", "Data not found");

		return listUser.get(0);
	}

	@Override
	public Role updateRole(Role Role) throws DAOException {
		return this.updateWithTxnManager(Role, Role.class);

	}

	@Override
	public RolePermission createRolePermission(RolePermission rolePermission) throws DAOException {	
		 return this.persistWiTxnManager(rolePermission, RolePermission.class);
	}

	@Override
	public RolePermission updateRolePermission(RolePermission rolePermission) throws DAOException {
		return this.updateWithTxnManager(rolePermission, RolePermission.class);
	}

	

}
