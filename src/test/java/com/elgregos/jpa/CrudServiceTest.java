package com.elgregos.jpa;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.elgregos.jpa.entities.Person;
import com.elgregos.test.arquillian.EarDeployment;

@RunWith(Arquillian.class)
public class CrudServiceTest {

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return new EarDeployment("crud.ear") {
			{
				webArchive.addAsWebInfResource("test-ds.xml");
				ejbModule.addClasses(PersonCrudService.class, PersonFacade.class, DataManagerForTest.class, QueryParameters.class)
				        .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
				earLibraries.add(ShrinkWrap.create(JavaArchive.class, "entity.jar").addPackage(Person.class.getPackage())
				        .addClasses(BaseEntity.class, CrudService.class, CrudServiceImlp.class)
				        .addAsManifestResource("test-persistence.xml", "persistence.xml"));
			}
		}.create();
	}

	@Inject
	DataManagerForTest dataManagerForTest;

	@Inject
	PersonFacade personFacade;

	private static boolean init = false;

	@Before
	public void setup() {
		if (CrudServiceTest.init == false) {
			dataManagerForTest.insertDefaultDatas();
			CrudServiceTest.init = true;
		}
	}

	@Test
	@Ignore
	public void failedTest() {
		Assert.fail();
	}

	@Test
	public void testCrudServiceImlp() {
		Assert.assertEquals(Person.class, personFacade.getPersonCrudServiceType());
	}

	@Test
	public void testCreate() {
		final Person person = new Person();
		person.setFirstname("Anne-Sophie");
		person.setLastname("Bévan");
		personFacade.createPerson(person);
		Assert.assertNotNull(person.getId());
	}

	@Test
	public void testFind() {
		final Person person = personFacade.findPerson(1L);
		Assert.assertNotNull(person);
		Assert.assertEquals(DataManagerForTest.personInformations.get(0)[0], person.getFirstname());
		Assert.assertEquals(DataManagerForTest.personInformations.get(0)[1], person.getLastname());

	}

	@Test
	public void testDelete() {
		final Long idToDelete = Long.valueOf(DataManagerForTest.personInformations.size() + 1);
		personFacade.deletePerson(idToDelete);
		Assert.assertNull(personFacade.findPerson(idToDelete));
	}

	@Test
	public void testUpdate() {
		final Long idToUpdate = 2L;
		final Person personFound = personFacade.findPerson(idToUpdate);
		Assert.assertNotNull(personFound);
		Assert.assertEquals(DataManagerForTest.personInformations.get(1)[0], personFound.getFirstname());
		personFound.setLastname("Duhem Bévan");
		personFacade.updatePerson(personFound);
		final Person personFoundAfterUpdate = personFacade.findPerson(idToUpdate);
		Assert.assertNotNull(personFoundAfterUpdate);
		Assert.assertEquals("Duhem Bévan", personFoundAfterUpdate.getLastname());
	}

	@Test
	public void testFindWithNamedQuery() {
		final List<Person> personsList = personFacade.findAll();
		Assert.assertNotNull(personsList);
		Assert.assertTrue(personsList.size() >= DataManagerForTest.personInformations.size() - 1);
	}

	@Test
	public void testFindWithNamedQueryWithResultLimit() {
		final List<Person> personsList = personFacade.findSome(5);
		Assert.assertNotNull(personsList);
		Assert.assertTrue(personsList.size() == 5);
	}

	@Test
	public void testFindWithNamedQueryWithParameters() {
		final List<Person> personsList = personFacade.findByLastname("Bévan");
		Assert.assertNotNull(personsList);
		Assert.assertTrue(personsList.size() == 3);
	}

	@Test
	public void testFindWithNamedQueryWithParametersAndResultLimit() {
		final List<Person> personsList = personFacade.findByLastname("Bévan", 5);
		Assert.assertNotNull(personsList);
		Assert.assertTrue(personsList.size() == 3);
	}

	@Test
	public void testFindByNativeQuery() {
		Assert.fail("Not yet implemented");
	}
}
