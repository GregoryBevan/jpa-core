package com.elgregos.jpa;

import java.util.List;

import com.elgregos.jpa.entities.Person;

public class PersonCrudService extends CrudServiceImlp<Person> {

	public List<Person> findAll() {
		return this.findWithNamedQuery("Person.findAll");
	}

	public List<Person> findSome(final int resultLimit) {
		return this.findWithNamedQuery("Person.findAll", 5);
	}

	public List<Person> findByLastname(final String lastname) {
		final QueryParameters queryParameters = QueryParameters.create("lastname", lastname);
		return this.findWithNamedQuery("Person.findByLastname", queryParameters);
	}

	public List<Person> findByLastname(final String lastname, final int resultLimit) {
		final QueryParameters queryParameters = QueryParameters.create("lastname", lastname);
		return this.findWithNamedQuery("Person.findByLastname", queryParameters, resultLimit);
	}

	public List<Person> findAllPersons() {
		return this.findByNativeQuery("select * from person");
	}

	public Person findPerson(final String firstname, final String lastname) {
		final QueryParameters queryParameters = QueryParameters.create("firstname", firstname).and("lastname", lastname);
		final List<Person> personList = this.findByNativeQuery("select * from person where firstname= :firstname and lastname= :lastname",
				queryParameters);
		if (personList.isEmpty()) {
			return null;
		}
		return personList.get(0);
	}
}