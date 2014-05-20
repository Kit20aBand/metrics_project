package com.metrics.persistence.dao.common;

import java.util.List;

public interface ISqlEditor {

	@SuppressWarnings("rawtypes")
	List findWithSql(final String sqlQuery);

	void runSql(final String sqlQuery);
}
