package com.elgregos.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.elgregos.jpa.entities.Person;

@Stateless
public class PersonFacade {

	@Inject
	PersonCrudService personCrudService;

	public Class<Person> getPersonCrudServiceType() {
		return this.personCrudService.getType();
	}

	public void createPerson(final Person person) {
		this.personCrudService.create(person);
	}

	public Person findPerson(final Long id) {
		return this.personCrudService.find(id);
	}

	public void deletePerson(final Long id) {
		this.personCrudService.delete(id);
	}

	public Person updatePerson(final Person person) {
		return this.personCrudService.update(person);
	}

	public List<Person> findAll() {
		return this.personCrudService.findAll();
	}

	public List<Person> findSome(final int resultLimit) {
		return this.personCrudService.findSome(resultLimit);
	}

	public List<Person> findByLastname(final String lastname) {
		return this.personCrudService.findByLastname(lastname);
	}

	public List<Person> findByLastname(final String lastname, final int resultLimit) {
		return this.personCrudService.findByLastname(lastname, resultLimit);
	}

	public List<Person> findAllPersons() {
		return this.personCrudService.findByNativeQuery("select * from person");
	}

	public Person findPerson(final String firstname, final String lastname) {
		return this.personCrudService.findPerson(firstname, lastname);

	}
}
