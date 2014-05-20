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
		return personCrudService.getType();
	}

	public void createPerson(Person person) {
		personCrudService.create(person);
	}

	public Person findPerson(final Long id) {
		return personCrudService.find(id);
	}

	public void deletePerson(final Long id) {
		personCrudService.delete(id);
	}

	public Person updatePerson(final Person person) {
		return personCrudService.update(person);
	}

	public List<Person> findAll() {
		return personCrudService.findAll();
	}

	public List<Person> findSome(int resultLimit) {
		return personCrudService.findSome(resultLimit);
	}

	public List<Person> findByLastname(String lastname) {
		return personCrudService.findByLastname(lastname);
	}

	public List<Person> findByLastname(String lastname, int resultLimit) {
		return personCrudService.findByLastname(lastname, resultLimit);
	}
}
