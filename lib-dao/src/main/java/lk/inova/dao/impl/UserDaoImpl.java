package lk.inova.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import lk.inova.dao.UserDao;
import lk.inova.dto.entity.User;
import lk.inova.exception.DAOException;
import lk.inova.util.ConnectionDao;

@Repository
public class UserDaoImpl extends ConnectionDao implements UserDao{

	private static final Logger log = Logger.getLogger(UserDaoImpl.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 6335507613446156560L;

	@Override
	public void createUser(User user) throws DAOException {
		this.persistWiTxnManager(user, User.class);
	}
	
	@Override
	public User readUserByName(String userName) throws DAOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uname", userName);
		List<User> listUser = this.excuteQuery("select o from User o where o.name=:uname", params , User.class);
		if(listUser == null || listUser.isEmpty())
			throw new DAOException("404", "Data not found"); 
		
		return listUser.get(0);
	}
	
	
	@Override
	public User readUserByUsername(String userName) throws DAOException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uname", userName);
		List<User> listUser = this.excuteQuery("select o from User o where o.username=:uname", params , User.class);
		if(listUser == null || listUser.isEmpty())
			throw new DAOException("404", "Data not found"); 
		return listUser.get(0);
	}

	@Override
	public void editUser(User user) throws DAOException {
		this.updateWithTxnManager(user, User.class);
		
	}
}
