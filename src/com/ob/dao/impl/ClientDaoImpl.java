package com.ob.dao.impl;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.ob.dao.*;
import com.ob.model.Client;


public class ClientDaoImpl implements ClientDAO {
	private static final Logger log = LoggerFactory.getLogger(ClientDAO.class);
	// property constants
	public static final String IDENTITY_ID = "identityId";
	public static final String TRUE_NAME = "trueName";
	public static final String SEX = "sex";
	public static final String PHONE = "phone";
	public static final String MAIL = "mail";
	public static final String ACCOUNT_TYPE = "accountType";
	public static final String USER_NAME = "userName";
	public static final String USER_PASSWORD = "userPassword";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void initDao() {
		// do nothing
	}

	public void save(Client transientInstance) {
		log.debug("saving Client instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Client persistentInstance) {
		log.debug("deleting Client instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Client findById(java.lang.Integer id) {
		log.debug("getting Client instance with id: " + id);
		try {
			Client instance = (Client) getCurrentSession().get(
					"com.ob.model.Client", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}


	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Client instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Client as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdentityId(Object identityId) {
		return findByProperty(IDENTITY_ID, identityId);
	}

	public List findByTrueName(Object trueName) {
		return findByProperty(TRUE_NAME, trueName);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}


	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	public List findAll() {
		log.debug("finding all Client instances");
		try {
			String queryString = "from Client";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Client merge(Client detachedInstance) {
		log.debug("merging Client instance");
		try {
			Client result = (Client) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Client instance) {
		log.debug("attaching dirty Client instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Client instance) {
		log.debug("attaching clean Client instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClientDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ClientDAO) ctx.getBean("ClientDAO");
	}
}
