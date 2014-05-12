package com.elgregos.jpa;

import javax.ejb.Stateless;

import com.elgregos.jpa.entities.Person;

@Stateless
public class PersonCrudService extends CrudServiceImlp<Person> {
}