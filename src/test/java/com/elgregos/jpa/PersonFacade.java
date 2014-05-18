package com.elgregos.jpa;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.elgregos.jpa.entities.Person;

@Stateless
public class PersonFacade {

	@Inject
	PersonCrudService personCrudService;

	public void createPerson(final Person person) {
		this.personCrudService.create(person);
	}

}
