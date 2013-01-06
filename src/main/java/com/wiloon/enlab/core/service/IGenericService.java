package com.wiloon.enlab.core.service;

import java.io.Serializable;
import java.util.List;

public interface IGenericService<T, PK extends Serializable> {
	public List<T> getList(String statement, T object);

	public void insert(String statement, T object);

	public void update(String statement, T object);

	public int getAString(String statement);
}
