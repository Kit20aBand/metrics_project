package com.metrics.persistence.dao.common;

import java.util.List;

public interface ISqlEditor {

	void runSql(final String sqlQuery);

	@SuppressWarnings("rawtypes")
	List findWithSql(final String sqlQuery);
}
