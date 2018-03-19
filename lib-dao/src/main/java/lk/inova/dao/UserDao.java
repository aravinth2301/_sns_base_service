package lk.inova.dao;

import lk.inova.dto.entity.User;
import lk.inova.exception.DAOException;

public interface UserDao {

	void createUser(User user) throws DAOException;
	
	void editUser(User user) throws DAOException;

	User readUserByName(String userName) throws DAOException;

	User readUserByUsername(String userName) throws DAOException;
}
