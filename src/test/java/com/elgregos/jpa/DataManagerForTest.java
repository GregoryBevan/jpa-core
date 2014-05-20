package com.elgregos.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;

import com.elgregos.jpa.entities.Person;

/**
 * Manage data for persistence test
 *
 * @author gbevan
 *
 */
@Stateless
public class DataManagerForTest {

	@PersistenceContext
	EntityManager entityManager;

	public static final List<String[]> personInformations;

	static int currentId = 1;

	static {
		personInformations = new ArrayList<>();
		DataManagerForTest.personInformations.add(new String[] { "Grégory", "Bévan" });
		DataManagerForTest.personInformations.add(new String[] { "Fanny", "Duhem" });
		DataManagerForTest.personInformations.add(new String[] { "Léony", "Bévan Duhem" });
		DataManagerForTest.personInformations.add(new String[] { "René", "Bévan" });
		DataManagerForTest.personInformations.add(new String[] { "Maryannick", "Bévan" });
		DataManagerForTest.personInformations.add(new String[] { "Personne", "A supprimer" });
	}

	public void insertDefaultDatas() {
		for (final String[] personInformation : DataManagerForTest.personInformations) {
			addPerson(personInformation);
		}
	}

	private void addPerson(String[] personInformation) {
		final Person person = new Person();
		person.setFirstname(personInformation[0]);
		person.setLastname(personInformation[1]);
		entityManager.persist(person);
		final Long id = person.getId();
		Assert.assertEquals(DataManagerForTest.currentId++, id.longValue());
		Assert.assertNotNull(entityManager.find(Person.class, id));
	}

}
