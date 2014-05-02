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
	public void deleteById(final int entityId) {
		getDao().deleteById(entityId);
	}

	@Override
	public T findOne(final int id) {
		return getDao().findOne(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public List<T> findAll(final int start, final int end) {
		return getDao().findAll(start, end);
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
	public T getOneResult(final String namedQueryName,
			final Map<String, Object> parameters) {
		return getDao().getOneResult(namedQueryName, parameters);
	}

	@Override
	public int countTotalRecord(final String namedQueryName) {
		return getDao().countTotalRecord(namedQueryName);
	}

	protected abstract ICommonOperations<T> getDao();

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName,
			final int start, final int end) {
		return getDao().findWithNamedQuery(namedQueryName, start, end);
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName,
			final Map<String, Object> parameters, final int start, final int end) {
		return getDao().findWithNamedQuery(namedQueryName, parameters, start,
				end);
	}

}
