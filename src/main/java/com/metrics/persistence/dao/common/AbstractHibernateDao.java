package com.metrics.persistence.dao.common;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.metrics.persistence.model.BaseEntity;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends BaseEntity> implements
ICommonOperations<T> {
	private Class<T> clazz;

	@Inject
	private SessionFactory sessionFactory;

	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = checkNotNull(clazzToSet);
	}

	@Override
	public final void create(final T entity) {
		checkNotNull(entity);
		// getCurrentSession().persist(entity);
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public final T update(final T entity) {
		checkNotNull(entity);
		return (T) getCurrentSession().merge(entity);
	}

	@Override
	public final void delete(final T entity) {
		checkNotNull(entity);
		getCurrentSession().delete(entity);
	}

	@Override
	public final void deleteById(final long entityId) {
		final T entity = findOne(entityId);
		checkState(entity != null);
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
	public List<T> findAll(final int start, final int end) {
		final Query query = getCurrentSession().createQuery(
				"from " + clazz.getName());
		query.setMaxResults(end - start);
		query.setFirstResult(start);
		return query.list();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName) {
		checkNotNull(namedQueryName);
		return getCurrentSession().getNamedQuery(namedQueryName).list();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName,
			final Map<String, Object> parameters) {
		return processNamedQueriesWithParameters(namedQueryName, parameters)
				.list();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName,
			final int start, final int end) {
		checkNotNull(namedQueryName);
		// checkArgument(start < 0, "must be positive: %s", start);
		// checkArgument(end < 0, "must be positive: %s", end);
		// checkArgument(end > start, "end %s must be bigger then start %s: %s",
		// end, start);
		final Query query = getCurrentSession().getNamedQuery(namedQueryName);
		query.setMaxResults(end - start);
		query.setFirstResult(start);
		return query.list();
	}

	@Override
	public Object getOneResult(final String namedQueryName,
			final Map<String, Object> parameters) {
		return processNamedQueriesWithParameters(namedQueryName, parameters)
				.uniqueResult();
	}

	@Override
	public int countTotalRecord(final String namedQueryName) {
		final Query query = getCurrentSession().getNamedQuery(namedQueryName);
		final Number number = (Number) query.uniqueResult();
		return number.intValue();
	}

	private Query processNamedQueriesWithParameters(
			final String namedQueryName, final Map<String, Object> parameters) {
		checkNotNull(namedQueryName);
		checkNotNull(parameters);
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