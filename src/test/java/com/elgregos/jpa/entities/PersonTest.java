/**
 * 
 */
package com.elgregos.jpa.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author gbevan
 *
 */
public class PersonTest {
	
	Person person;
	
	@Before
	public void setUp() {
		person = new Person();
		person.setFirstname("Gregory");
		person.setLastname("Bevan");
	}

	/**
	 * Test method for {@link com.elgregos.jpa.entities.Person#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Person personToCompare = new Person();
		personToCompare.setLastname("Bevan");
		personToCompare.setFirstname("Gregory");
		Assert.assertTrue(person.equals(personToCompare));
	}

	/**
	 * Test method for {@link com.elgregos.jpa.entities.Person#toString()}.
	 */
	@Test
	public void testToString() {
		Assert.assertEquals("Person(super=BaseEntity(id=null), lastname=Bevan, firstname=Gregory)",  person.toString());
	}

}
