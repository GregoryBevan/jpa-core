package com.elgregos.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public abstract class CrudServiceImlp<T> implements CrudService<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	public Class<T> type;

	@SuppressWarnings("unchecked")
	public CrudServiceImlp() {
		final Type t = getClass().getGenericSuperclass();
		final ParameterizedType pt = (ParameterizedType) t;
		this.type = (Class<T>)pt.getActualTypeArguments()[0];
	}

	@Override
	public T create(final T t) {
		this.entityManager.persist(t);
		this.entityManager.flush();
		this.entityManager.refresh(t);
		return t;
	}

	@Override
	public T find(final Object id) {
		return this.entityManager.find(this.type, id);
	}

	@Override
	public void delete(final Object id) {
		final Object ref = this.entityManager.getReference(this.type, id);
		this.entityManager.remove(ref);
	}

	@Override
	public T update(final T t) {
		return this.entityManager.merge(t);
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName) {
		return this.entityManager.createNamedQuery(namedQueryName, this.type).getResultList();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName, final Map<String, Object> parameters) {
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}

	@Override
	public List<T> findWithNamedQuery(final String queryName, final int resultLimit) {
		return this.entityManager.createNamedQuery(queryName, this.type).setMaxResults(resultLimit).getResultList();
	}

	@Override
	public List<T> findWithNamedQuery(final String namedQueryName, final Map<String, Object> parameters, final int resultLimit) {
		final Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		final TypedQuery<T> query = this.entityManager.createNamedQuery(namedQueryName, this.type);
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		for (final Entry<String, Object> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNativeQuery(final String sql) {
		return this.entityManager.createNativeQuery(sql, this.type).getResultList();
	}

	@Override
	public Class<T> getType() {
		return this.type;
	}
}
