package lk.inova.sbeans;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import lk.inova.dto.entity.Permission;
import lk.inova.dto.entity.Role;
import lk.inova.dto.entity.RolePermission;
import lk.inova.dto.entity.User;
import lk.inova.exception.DAOException;
import lk.inova.txn.TxnPermissionservice;
import lk.inova.txn.TxnRoleservice;
import lk.inova.txn.TxnUserservice;
import lk.inova.util.SERVICE_REST_URL;
import lk.inova.util.TBLRecordStatus;

@Component
public class StartupBean implements Serializable {

	/**
	 * 
	 */

	@Autowired
	private StartUpConfigProperty startUpConfigProperty;

	@Autowired
	private TxnUserservice txnUserservice;

	@Autowired
	private TxnPermissionservice txnPermissionservice;

	@Autowired
	private TxnRoleservice txnRoleservice;

	private static final long serialVersionUID = 2984205677419249643L;
	private static final Logger log = Logger.getLogger(StartupBean.class);

	private User user;

	@PostConstruct
	public void init() {
		user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		addSuperUser();
		addpermission();
		addAdminRole();
		addRole();

	}

	private void addAdminRole() {
		
		try {
			startUpConfigProperty.getRoleAdmin().setStatus(TBLRecordStatus.ACTIVE.getId());
			startUpConfigProperty.getRoleAdmin().setMetaData(true);
			
			List<Permission> tempListPermission = txnPermissionservice.readPermission(null);
			List<RolePermission> tempListRolePermission = new ArrayList<RolePermission>();
			Date createdDate = new Date();
			for (Permission permission : tempListPermission) {
				
				RolePermission tempRolePermission = new RolePermission(null, permission);
				tempRolePermission.setMetaData(true);
				tempRolePermission.setCreateDate(createdDate);
				tempRolePermission.setModifiedDate(createdDate);
				tempListRolePermission.add(tempRolePermission );
			}
		
			txnRoleservice.addRoleWithTxn(startUpConfigProperty.getRoleAdmin(),tempListRolePermission);
			
		} catch (DataIntegrityViolationException e) {
			log.error(e);
		} catch (DAOException e) {
			log.error(e);
		}
	}
	private void addRole() {
		List<TempRoleFunctionDTO> tempList = startUpConfigProperty.getListMetaZoneRolesPermission();// TODO Auto-generated method stub

		Role parentRole = null;

		for (int i = 0; i < tempList.size(); i++) {
			TempRoleFunctionDTO array_element = tempList.get(i);
			List<RolePermission> listRolePermission = new ArrayList<RolePermission>();
			try {
				Date createdDate = new Date();
				Role tempRole = new Role();
				tempRole = array_element.getRole();
				tempRole.setCreateDate(createdDate);
				tempRole.setModifiedDate(createdDate);
				tempRole.setCreatedBy(user);
				tempRole.setModifiedBy(user);
				tempRole.setStatus(TBLRecordStatus.ACTIVE.getId());
				tempRole.setMetaData(true);
				if (parentRole != null)
					tempRole.setParentRole(parentRole);
				//List<RolePermission> tempListRolePermission = new ArrayList<RolePermission>();
				listRolePermission = txnRoleservice.addRoleWithTxn(tempRole,getPermissionsByCode(array_element.getListPermission()) );
				if (i == 0)
					parentRole = listRolePermission.get(0).getRole();

			} catch (DataIntegrityViolationException e) {
				log.error(e);
			} catch (DAOException e) {
				log.error(e);
			}

		}
	}

	private List<RolePermission> getPermissionsByCode(List<Permission> listPermission) {
		List<RolePermission> tempListPermission = new ArrayList<RolePermission>();
		
		for (int i = 0; i < listPermission.size(); i++) {
			Permission array_element = listPermission.get(i);
			
			try {
				List<Permission> tempPermission = txnPermissionservice.readPermission(array_element);
				tempListPermission.add(new RolePermission(null, tempPermission.get(i)));
			} catch (DataIntegrityViolationException e) {
				log.error(e);
			} catch (DAOException e) {
				log.error(e);
			}
		}
		
		return tempListPermission;
	}

	private void addpermission() {
		Field[] fields = SERVICE_REST_URL.class.getDeclaredFields();
		SERVICE_REST_URL temp = new SERVICE_REST_URL();

		Permission permission = null;
		for (int i = 0; i < fields.length; i++) {

			try {
				Date createdDate = new Date();
				permission = new Permission();
				permission.setCode(fields[i].getName());
				permission.setScope(fields[i].get(temp).toString());
				permission.setCreateDate(createdDate);
				permission.setModifiedDate(createdDate);
				permission.setCreatedBy(user);
				permission.setModifiedBy(user);
				permission.setStatus(TBLRecordStatus.ACTIVE.getId());

				txnPermissionservice.addPermissionWithTxn(permission);
				log.info(fields[i].getName() + "  " + fields[i].get(temp));
			} catch (IllegalArgumentException e) {
				log.error(e);
			} catch (IllegalAccessException e) {
				log.error(e);
			} catch (DataIntegrityViolationException e) {
				log.error(e);
			} catch (DAOException e) {
				log.error(e);
			}
		}

	}

	private void addSuperUser() {
		try {
			user = txnUserservice.searchUserByUsername("admin");
			log.info("Super user already exist");
		} catch (DataIntegrityViolationException e) {
			log.error(e);
		} catch (DAOException e) {
			log.error(e);
			if ("404".equals(e.getErrorCode())) {
				try {
					txnUserservice.addUserWithTxn(user);
					log.info("Super user newly added");
				} catch (DataIntegrityViolationException e1) {
					log.error(e1);
				} catch (DAOException e1) {
					log.error(e1);
				}
			}
		}
	}

}
