package com.wiloon.enlab.core.dao.impl;

import com.wiloon.enlab.core.dao.IGenericDao;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author wiloon
 * 
 * @param <T>
 * @param <PK>
 *            test for in line link {@link com.wiloon.enlab.domain.ECP#getID
 *            labelTest}
 */
public class GenericDao<T, PK extends Serializable> extends SqlMapClientDaoSupport implements
	IGenericDao<T, PK> {
    protected final Logger logger = Logger.getLogger(GenericDao.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getList(String statement, T object) {
	logger.info("get list : " + statement);
	List<T> list = null;
	try {
	    list = getSqlMapClientTemplate().queryForList(statement, object);
	} catch (Exception e) {
	    logger.info(e);
	}

	//logger.info("list size: " + list.size());

	return list;
    }

    @Override
    public void insert(String statement, T object) {
	getSqlMapClientTemplate().insert(statement, object);

    }

    @Override
    public void update(String statement, T object) {
	try {
	    getSqlMapClientTemplate().update(statement, object);
	} catch (Exception e) {
	    logger.info(e);
	}

    }

    @Override
    public int getAInt(String statement) {

	return (Integer) getSqlMapClientTemplate().queryForObject(statement);
    }

}
