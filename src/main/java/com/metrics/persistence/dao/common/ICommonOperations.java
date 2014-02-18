package com.metrics.persistence.dao.common;

import java.util.List;
import java.util.Map;

import com.metrics.persistence.model.BaseEntity;

public interface ICommonOperations<T extends BaseEntity> {

	T update(T entity);

	void create(T entity);

	void delete(T entity);

	void deleteById(long entityId);

	T findOne(long id);

	List<T> findAll();

	List<T> findWithNamedQuery(String namedQueryName);

	List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters);

	Object getOneResult(String namedQueryName, Map<String, Object> parameters);

}
