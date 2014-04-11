package com.metrics.persistence.dao.common;

import java.util.List;
import java.util.Map;

import com.metrics.persistence.model.BaseEntity;

public interface ICommonOperations<T extends BaseEntity> {

	T update(T entity);

	void create(T entity);

	void delete(T entity);

	void deleteById(int entityId);

	T findOne(int id);

	List<T> findAll();

	List<T> findAll(int start, int end);

	List<T> findWithNamedQuery(String namedQueryName);

	List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters);

	List<T> findWithNamedQuery(String namedQueryName, int start, int end);

	Object getOneResult(String namedQueryName, Map<String, Object> parameters);

	int countTotalRecord(String namedQueryName);

}
