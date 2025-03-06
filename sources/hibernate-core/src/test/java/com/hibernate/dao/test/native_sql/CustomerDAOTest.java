package com.hibernate.dao.test.native_sql;

import com.hibernate.dao.test.BaseDAOTest;
import com.hibernate.native_sql.Customer;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class CustomerDAOTest extends BaseDAOTest {

	private void initDataTest() {
		
		Customer cust1 = new Customer();
		cust1.setName("anhvi1");
		cust1.setAge(10);
		
		Customer cust2 = new Customer();
		cust2.setName("anhvi2");
		cust2.setAge(10);

		em.persist(cust1);
		em.persist(cust2);
		em.flush();
	}
	
	private void cleanUpDataTest() {
		em.createQuery("DELETE FROM Customer").executeUpdate();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void entityQueries() {
		initDataTest();
		
		List<Customer> customers = em.createNativeQuery("SELECT * FROM customer", Customer.class ).getResultList();
		
		assertEquals(customers.size(), 2);
		
		cleanUpDataTest();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void entityQueries2() {
		initDataTest();
		
		List<Customer> customers = em.createNativeQuery("SELECT * FROM customer c where c.name = :name", Customer.class )
				.setParameter("name", "anhvi2")
				.getResultList();

		assertEquals(customers.size(), 1);
		
		cleanUpDataTest();
	}
	
	@Test
	public void entityQueries3() {
		initDataTest();
		
		Customer customer = (Customer) em.createNativeQuery("SELECT * FROM customer c where c.name = :name", Customer.class )
				.setParameter("name", "anhvi2")
				.getSingleResult();

		assertEquals(customer.getName(), "anhvi2");
		
		cleanUpDataTest();
	}
	
}