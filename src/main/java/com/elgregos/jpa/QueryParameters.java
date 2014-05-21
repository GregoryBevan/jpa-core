package com.elgregos.jpa;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines query parameters
 *
 * @author gbevan
 */
public class QueryParameters {

	private Map<String, Object> parameters = null;

	private QueryParameters(final String name, final Object value) {
		this.parameters = new HashMap<String, Object>();
		this.parameters.put(name, value);
	}

	/**
	 * Create query parameter
	 *
	 * @param name Name of the parameter
	 * @param value Value of the parameter
	 * @return
	 */
	public static QueryParameters create(final String name, final Object value) {
		return new QueryParameters(name, value);
	}

	/**
	 * Adds new query parameter
	 *
	 * @param name Name of the parameter
	 * @param value Value of the parameter
	 * @return
	 */
	public QueryParameters and(final String name, final Object value) {
		this.parameters.put(name, value);
		return this;
	}

	/**
	 * Returns query parameters map
	 *
	 * @return Parameters map
	 */
	public Map<String, Object> getParameters() {
		return this.parameters;
	}
}
