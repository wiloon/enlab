package com.wiloon.enlab.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.wiloon.enlab.core.dao.IGenericDao;
import com.wiloon.enlab.core.service.IGenericService;


/**
 * 
 * @author wiloon
 *
 * @param <T>
 * @param <PK>
 * @see com.wiloon.enlab.core.dao.impl.GenericDao
 */
public class GenericService<T, PK extends Serializable> implements
		IGenericService<T, PK> {
	protected final Logger logger = Logger.getLogger(GenericService.class);
	protected IGenericDao<T, PK> genericDao;

	public GenericService(final IGenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	public List<T> getList(String statement, T object) {

		return genericDao.getList(statement, object);
	}

	@Override
	public void insert(String statement, T object) {
		genericDao.insert(statement, object);

	}

	public void update(String statement, T object) {
		genericDao.update(statement, object);
	}

	@Override
	public int getAString(String statement) {

		return genericDao.getAInt(statement);
	}

}
