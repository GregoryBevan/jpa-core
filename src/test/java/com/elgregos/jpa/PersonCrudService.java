package com.elgregos.jpa;

import java.util.List;
import java.util.Map;

import com.elgregos.jpa.entities.Person;

public class PersonCrudService extends CrudServiceImlp<Person> {

	public List<Person> findAll() {
		return this.findWithNamedQuery("Person.findAll");
	}

	public List<Person> findSome(int resultLimit) {
		return this.findWithNamedQuery("Person.findAll", 5);
	}

	public List<Person> findByLastname(String lastname) {
		final Map<String, Object> parameters = QueryParameters.with("lastname", lastname).getParameters();
		return this.findWithNamedQuery("Person.findByLastname", parameters);
	}

	public List<Person> findByLastname(String lastname, int resultLimit) {
		final Map<String, Object> parameters = QueryParameters.with("lastname", lastname).getParameters();
		return this.findWithNamedQuery("Person.findByLastname", parameters, resultLimit);
	}
}