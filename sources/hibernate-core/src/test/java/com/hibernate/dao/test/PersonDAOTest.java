package com.hibernate.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import com.hibernate.entity.phone.Person;
import com.hibernate.entity.phone.Phone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class PersonDAOTest extends BaseDAOTest {
	

	
	@Test
	public void savePersonTest() {
		Person person1 = new Person();
		
		Phone phone1 = new Phone();
		phone1.setPhoneNumber("0329767343");
		
		Phone phone2 = new Phone();
		phone2.setPhoneNumber("0329767343");
		
		person1.getPhones().add(phone1);
		person1.getPhones().add(phone2);
		
		em.persist(person1);
		em.flush();
		
		List<Person> personSaved = em.createQuery("select p from Person p", Person.class).getResultList();
		
		assertEquals(personSaved.size(), 1);
		assertEquals(personSaved.get(0).getPhones().size(), 2);
		
		person1.getPhones().remove(phone1);
		assertEquals(personSaved.get(0).getPhones().size(), 1);
		
	}

}
