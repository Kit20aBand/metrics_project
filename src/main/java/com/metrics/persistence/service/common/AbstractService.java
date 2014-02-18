package com.metrics.persistence.service.common;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.metrics.persistence.dao.common.ICommonOperations;
import com.metrics.persistence.model.BaseEntity;

@Transactional
public abstract class AbstractService<T extends BaseEntity> implements
		ICommonOperations<T> {

	@Override
	public void create(final T entity) {
		getDao().create(entity);
	}

	@Override
	public T update(final T entity) {
		return getDao().update(entity);
	}

	@Override
	public void delete(final T entity) {
		getDao().delete(entity);
	}

	@Override
	public void deleteById(final long entityId) {
		getDao().deleteById(entityId);
	}

	@Override
	public T findOne(final long id) {
		return getDao().findOne(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName) {
		return getDao().findWithNamedQuery(namedQueryName);
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName,
			final Map<String, Object> parameters) {
		return getDao().findWithNamedQuery(namedQueryName, parameters);
	}

	@Override
	public Object getOneResult(final String namedQueryName,
			final Map<String, Object> parameters) {
		return getDao().getOneResult(namedQueryName, parameters);
	}

	protected abstract ICommonOperations<T> getDao();

}
