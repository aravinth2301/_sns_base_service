package lk.inova.txn;

import org.springframework.dao.DataIntegrityViolationException;

import lk.inova.dto.entity.User;
import lk.inova.exception.DAOException;

public interface TxnUserservice {

	
	void addUserWithTxn(User user) throws DAOException,DataIntegrityViolationException;
	
	void editUserWithTxn(User user) throws DAOException,DataIntegrityViolationException;
	
	User searchUserByUsername(String userName) throws DAOException,DataIntegrityViolationException;
}
