package com.metrics.persistence.dao.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.common.base.Preconditions;
import com.metrics.persistence.model.BaseEntity;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends BaseEntity> implements
		ICommonOperations<T> {
	private Class<T> clazz;

	@Inject
	private SessionFactory sessionFactory;

	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = Preconditions.checkNotNull(clazzToSet);
	}

	@Override
	public final void create(final T entity) {
		Preconditions.checkNotNull(entity);
		// getCurrentSession().persist(entity);
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public final T update(final T entity) {
		Preconditions.checkNotNull(entity);
		return (T) getCurrentSession().merge(entity);
	}

	@Override
	public final void delete(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().delete(entity);
	}

	@Override
	public final void deleteById(final long entityId) {
		final T entity = findOne(entityId);
		Preconditions.checkState(entity != null);
		delete(entity);
	}

	@Override
	public final T findOne(final long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@Override
	public final List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName())
				.list();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName) {
		Preconditions.checkNotNull(namedQueryName);
		return getCurrentSession().getNamedQuery(namedQueryName).list();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName,
			final Map<String, Object> parameters) {
		return processNamedQueriesWithParameters(namedQueryName, parameters)
				.list();
	}

	@Override
	public Object getOneResult(final String namedQueryName,
			final Map<String, Object> parameters) {
		return processNamedQueriesWithParameters(namedQueryName, parameters)
				.uniqueResult();
	}

	private Query processNamedQueriesWithParameters(
			final String namedQueryName, final Map<String, Object> parameters) {
		Preconditions.checkNotNull(namedQueryName);
		Preconditions.checkNotNull(parameters);
		final Set<Map.Entry<String, Object>> rawParameters = parameters
				.entrySet();
		final Query query = getCurrentSession().getNamedQuery(namedQueryName);
		for (final Map.Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}

	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}