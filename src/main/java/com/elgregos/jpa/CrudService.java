package com.elgregos.jpa;

import java.util.List;

public interface CrudService<T> {

	T create(final T t);

	T find(final Object id);

	T update(final T t);

	void delete(final Object id);

	List<T> findWithNamedQuery(final String queryName);

	List<T> findWithNamedQuery(final String queryName, final int resultLimit);

	List<T> findWithNamedQuery(final String namedQueryName, final QueryParameters queryParameters);

	List<T> findWithNamedQuery(final String namedQueryName, final QueryParameters queryParameters, final int resultLimit);

	List<T> findByNativeQuery(final String sql);

	List<T> findByNativeQuery(final String sql, final QueryParameters parameters);

	Class<T> getType();
}
