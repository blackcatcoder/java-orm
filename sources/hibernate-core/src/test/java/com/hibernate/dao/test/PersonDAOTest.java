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

public class PersonDAOTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction et;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("hibernate-core");
	}

	@Before
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		et = em.getTransaction();
		et.begin();
	}

	@After
	public void tearDown() throws Exception {
		et.commit();
		em.close();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		emf.close();
	}
	
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
