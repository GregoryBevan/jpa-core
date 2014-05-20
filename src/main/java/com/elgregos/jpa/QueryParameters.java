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

	private QueryParameters(String name, Object value) {
		parameters = new HashMap<String, Object>();
		parameters.put(name, value);
	}

	/**
	 * Adds first query parameter
	 *
	 * @param name Name of the parameter
	 * @param value Value of the parameter
	 * @return
	 */
	public static QueryParameters with(String name, Object value) {
		return new QueryParameters(name, value);
	}

	/**
	 * Adds new query parameter
	 *
	 * @param name Name of the parameter
	 * @param value Value of the parameter
	 * @return
	 */
	public QueryParameters and(String name, Object value) {
		parameters.put(name, value);
		return this;
	}

	/**
	 * Returns query parameters map
	 *
	 * @return Parameters map
	 */
	public Map<String, Object> getParameters() {
		return parameters;
	}
}
