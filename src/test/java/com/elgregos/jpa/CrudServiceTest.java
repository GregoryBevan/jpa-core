package com.elgregos.jpa;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.elgregos.jpa.entities.Person;
import com.elgregos.test.arquillian.EarDeployment;

@RunWith(Arquillian.class)
public class CrudServiceTest {

	// @Deployment(order = 1, name = "dep1", testable = false)
	// public static JavaArchive createTestDeployment() {
	// final File postgresJar =
	// Maven.resolver().resolve("postgresql:postgresql:9.1-901.jdbc4").withoutTransitivity().asSingleFile();
	// return ShrinkWrap.createFromZipFile(JavaArchive.class, postgresJar);
	// }

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return new EarDeployment("crud.ear") {
			{
				// this.webArchive.addAsWebInfResource("postgres-ds.xml");
				this.webArchive.addAsWebInfResource("test-ds.xml");
				this.ejbModule.addClasses(PersonCrudService.class, PersonFacade.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
				this.earLibraries.add(ShrinkWrap.create(JavaArchive.class, "entity.jar").addPackage(Person.class.getPackage())
						.addClasses(BaseEntity.class, CrudService.class, CrudServiceImlp.class)
						.addAsManifestResource("test-persistence.xml", "persistence.xml"));
			}
		}.create();
	}

	@Inject
	PersonFacade personFacade;

	@Test
	public void testCrudServiceImlp() {
		Assert.fail();
		// Assert.assertEquals(Person.class, this.personCrudService.getType());
	}

	@Test
	public void testCreate() {
		final Person person = new Person();
		this.personFacade.createPerson(person);
		final Person person2 = new Person();
		this.personFacade.createPerson(person2);
		Assert.assertEquals(1, person.getId().longValue());
		Assert.assertEquals(3, person2.getId().longValue());
	}
}
