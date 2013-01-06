package com.wiloon.enlab.core.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, PK extends Serializable> {
	public List<T> getList(String statement, T object);

	public void insert(String statement, T object);

	public void update(String statement, T object);

	public int getAInt(String statement);
}
