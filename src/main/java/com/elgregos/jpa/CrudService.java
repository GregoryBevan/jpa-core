package com.elgregos.jpa;

import java.util.List;
import java.util.Map;

public interface CrudService<T> {

	T create(final T t);

	T find(final Object id);

	T update(final T t);

	void delete(final Object id);

	List<T> findWithNamedQuery(final String queryName);

	List<T> findWithNamedQuery(final String queryName, final int resultLimit);

	List<T> findWithNamedQuery(final String namedQueryName, final Map<String, Object> parameters);

	List<T> findWithNamedQuery(final String namedQueryName, final Map<String, Object> parameters, final int resultLimit);

	List<T> findByNativeQuery(final String sql);

	Class<T> getType();
}
