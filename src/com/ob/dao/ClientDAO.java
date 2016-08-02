package com.ob.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.ob.model.Client;

/**
 * A data access object (DAO) providing persistence and search support for
 * Client entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.ob.model.Client
 * @author MyEclipse Persistence Tools
 */
@Transactional
public interface ClientDAO {

	public void setSessionFactory(SessionFactory sessionFactory);

	public Session getCurrentSession();

	void initDao();

	public void save(Client transientInstance);

	public void delete(Client persistentInstance);

	public Client findById(java.lang.Integer id);

	public List findByProperty(String propertyName, Object value);

	public List findByIdentityId(Object identityId);

	public List findByPhone(Object phone);

	public List findByUserName(Object userName);

	public List findAll();

	public Client merge(Client detachedInstance);

	public void attachDirty(Client instance);

	public void attachClean(Client instance);
}