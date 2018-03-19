package lk.inova.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.log.Log;

import lk.inova.dao.PermissionDao;
import lk.inova.dto.entity.Permission;
import lk.inova.exception.DAOException;
import lk.inova.util.ConnectionDao;

@Repository
public class PermissionDaoImpl extends ConnectionDao implements PermissionDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4088966899838829454L;

	private static final Logger log = Logger.getLogger(PermissionDaoImpl.class);
	
	@Override
	public void createPermission(Permission permission) throws DAOException {
		this.persistWiTxnManager(permission, Permission.class);

	}

	@Override
	public List<Permission> readPermissionByFilters(Permission permission) throws DAOException {
		Map<String, Object> params = new HashMap<String, Object>();

		String baseHQL = "select o from Permission o";
		List<String> listFilter = new ArrayList<String>();

		if (permission != null) {

			if (permission.getCode() != null) {
				listFilter.add("o.code = :pcode");
				params.put("pcode", permission.getCode());
			}

			if (permission.getStatus() != null) {
				listFilter.add("o.status=:pstatus");
				params.put("pstatus", permission.getStatus());
			}
		}

		if (!listFilter.isEmpty()) {
			String conditions = "";

			for (int i = 0; i < listFilter.size(); i++) {
				String array_element = listFilter.get(i);
				if (i != 0)
					conditions = conditions + " and " + array_element;
				else
					conditions = conditions + " " + array_element;
			}

			baseHQL = baseHQL + " where" + conditions;
		}

		log.info(baseHQL);
		
		List<Permission> listUser = this.excuteQuery(baseHQL, params, Permission.class);
		if (listUser == null && listUser.isEmpty())
			throw new DAOException("404", "Data not found");

		return listUser;
	}

	@Override
	public void updatePermission(Permission permission) throws DAOException {
		this.updateWithTxnManager(permission, Permission.class);

	}

}
