package com.metrics.persistence.dao.common;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SqlEditor implements ISqlEditor {

	@Inject
	private SessionFactory sessionFactory;

	@Override
	@SuppressWarnings("rawtypes")
	public List findWithSql(final String sqlQuery) {
		final Query query = sessionFactory.getCurrentSession().createSQLQuery(
				sqlQuery);
		return query.list();
	}

	@Override
	public void runSql(final String sqlQuery) {
		final Query query = sessionFactory.getCurrentSession().createSQLQuery(
				sqlQuery);
		query.executeUpdate();
	}
}
