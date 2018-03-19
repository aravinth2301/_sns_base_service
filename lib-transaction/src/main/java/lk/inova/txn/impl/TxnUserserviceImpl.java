package lk.inova.txn.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lk.inova.dao.UserDao;
import lk.inova.dto.entity.User;
import lk.inova.exception.DAOException;
import lk.inova.txn.TxnUserservice;

@Repository
public class TxnUserserviceImpl implements TxnUserservice{

	private static final Logger log = Logger.getLogger(TxnUserserviceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void addUserWithTxn(User user) throws DAOException,DataIntegrityViolationException {
		userDao.createUser(user);
	}

	@Override
	@Transactional
	public void editUserWithTxn(User user) throws DAOException,DataIntegrityViolationException {
		userDao.editUser(user);
		
	}

	@Override
	public User searchUserByUsername(String userName) throws DAOException, DataIntegrityViolationException {
		return userDao.readUserByUsername(userName);
	}

	
}
