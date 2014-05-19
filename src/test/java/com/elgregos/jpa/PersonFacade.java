package com.elgregos.jpa;

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

}
