package lk.inova.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import lk.inova.dto.entity._CommonColumn;
import lk.inova.exception.DAOException;

public abstract class ConnectionDao implements Serializable {

	/**
	 * 
	 */
	private static final Logger log = Logger.getLogger(ConnectionDao.class);
	private static final long serialVersionUID = -461210978501362135L;

	@Value(value = "${admin.hibernate.connection.url}")
	public String url;

	@Autowired
	@Qualifier(value = "localSession")
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> findAll(Class<T> clas) throws DAOException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			List<T> tempList = session.createCriteria(clas).list();
			tx.commit();
			return tempList;
		} catch (GenericJDBCException e) {
			log.error(e);

			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e) {
			log.error(e);

			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> excuteQuery(String strQuery, Map<String, Object> params, Class<T> type) throws DAOException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(strQuery);
			if (params != null && !params.isEmpty()) {
				Set<String> keySet = params.keySet();
				for (String key : keySet) {
					Object tempObj = params.get(key);
					if (tempObj instanceof List)
						query.setParameterList(key, (Collection) tempObj);
					else
						query.setParameter(key, tempObj);
				}
			}
			List<T> tempList = query.list();
			tx.commit();
			return tempList;
		} catch (RuntimeException e) {
			log.error(e);

			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> T findEntityById(Class<T> type, Object key) throws DAOException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			T tempEntity = ((T) session.get(type, (Serializable) key));
			tx.commit();
			return tempEntity;
		} catch (RuntimeException e) {
			log.error(e);

			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
	}

	protected <T> T persist(Object object, Class<T> objType) throws DAOException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Serializable serial = session.save(object);
			@SuppressWarnings("unchecked")
			T persistObj = (T) session.load(objType, serial);
			tx.commit();
			return persistObj;
		} catch (ConstraintViolationException e) {
			log.error(e);
			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e) {
			log.error(e);

			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
	}

	protected <T> T persistWiTxnManager(Object object, Class<T> objType) throws DAOException {
		// Session session = txHManager.getSessionFactory().getCurrentSession();
		Session session = sessionFactory.getCurrentSession();

		try {
			if (object instanceof _CommonColumn) {
				_CommonColumn baseObject = (_CommonColumn) object;
				baseObject.setCurrentHash(baseObject.getChildCurrentHash());
				
			}
			
			Serializable serial = session.save(object);
			@SuppressWarnings("unchecked")
			T persistObj = (T) session.load(objType, serial);
			return persistObj;
		} catch (ConstraintViolationException e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> T update(Object obj, Class<?> type) throws DAOException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			T updateObj = ((T) session.merge(type.getName(), obj));
			tx.commit();
			return updateObj;
		} catch (ConstraintViolationException e) {
			log.error(e);
			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e) {
			log.error(e);

			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> T updateWithTxnManager(Object obj, Class<?> type) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			if (obj instanceof _CommonColumn) {
				_CommonColumn baseObject = (_CommonColumn) obj;
				baseObject.setCurrentHash(baseObject.getChildCurrentHash());
				
			}
			T updateObj = ((T) session.merge(type.getName(), obj));
			return updateObj;
		} catch (ConstraintViolationException e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

	protected void delete(Class<?> clsType, Object key) throws DAOException {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Object temp = session.get(clsType, (Serializable) key);
			session.delete(clsType.getName(), temp);
			tx.commit();
		} catch (RuntimeException e) {
			log.error(e);

			if (tx != null)
				tx.rollback();
			throw new DAOException(e.getMessage());
		} finally {
			if (session != null) {
				session.clear();
				session.close();
			}
		}
	}

	protected void deleteTxnManager(Class<?> clsType, Object key) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			Object temp = session.get(clsType, (Serializable) key);
			session.delete(clsType.getName(), temp);
		} catch (RuntimeException e) {
			log.error(e);
			throw new DAOException(e.getMessage());
		}
	}

}
