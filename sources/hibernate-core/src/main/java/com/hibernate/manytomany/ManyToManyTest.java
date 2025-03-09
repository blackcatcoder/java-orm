package com.hibernate.manytomany;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.hibernate.BaseDAOTest;

import java.util.List;

public class ManyToManyTest extends BaseDAOTest {
	
	@Test
	public void test() {
		
		// 1:
		Person p1 = new Person();
		p1.setDesc("person description");
		
		em.persist(p1);
		
		List<Person> persons = em.createQuery("select p from Person p", Person.class).getResultList();
		assertEquals(persons.size(), 1);
		assertEquals(persons.get(0).getDesc(), "person description");
		assertEquals(persons.get(0).getPersonAddresses().size(), 0);

		
		// 2:
		Address a1 = new Address();
		a1.setAddressDesc("address description");
		
		em.persist(a1);
		
		List<Address> addresses = em.createQuery("select a from Address a", Address.class).getResultList();
		
		assertEquals(addresses.size(), 1);
		assertEquals(addresses.get(0).getAddressDesc(), "address description");
		assertEquals(addresses.get(0).getPersonAddresses().size(), 0);
		
		// 3:
		PersonAddress pa1 = new PersonAddress(p1, a1);
		em.persist(pa1);
		
		persons = em.createQuery("select p from Person p", Person.class).getResultList();
		assertEquals(persons.size(), 1);
		assertEquals(persons.get(0).getDesc(), "person description");
		assertEquals(persons.get(0).getPersonAddresses().size(), 1);
		
		// 4
		addresses = em.createQuery("select a from Address a", Address.class).getResultList();
		
		assertEquals(addresses.size(), 1);
		assertEquals(addresses.get(0).getAddressDesc(), "address description");
		assertEquals(addresses.get(0).getPersonAddresses().size(), 1);
	}
	
	@Test
	public void test2() {
		
		Category c1 = new Category();
		c1.setDesc("category description");
		
		Item i1 = new Item();
		i1.setDesc("item description");
		
		c1.getItems().add(i1);
		i1.getCategories().add(c1);
		
		// 1:
		em.persist(c1);
		//em.persist(i1);
		
		List<Category> categories = em.createQuery("select c from Category c", Category.class).getResultList();
		
		assertNotNull(categories);
		assertEquals(categories.size(), 1);
		assertEquals(categories.get(0).getItems().size(), 1);
		
		System.out.println(categories.get(0).getId());
		
		// 2:
		List<Item> items = em.createQuery("select i from Item i", Item.class).getResultList();
		System.out.println(items.get(0).getId());
		assertNotNull(items);
		assertEquals(items.get(0).getDesc(), "item description");
		
		// 3:
		Item i2 = new Item();
		i2.setDesc("item description 2");
		c1.getItems().add(i2);
		em.merge(c1);
		
		List<Category> categories2 = em.createQuery("select c from Category c", Category.class).getResultList();
		
		assertNotNull(categories2);
		assertEquals(categories2.size(), 1);
		assertEquals(categories2.get(0).getItems().size(), 2);
	}

}
