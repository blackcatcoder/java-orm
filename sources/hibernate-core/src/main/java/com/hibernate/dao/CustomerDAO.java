package com.hibernate.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hibernate.entity.Customer;

public class CustomerDAO {
	
	private static final CustomerDAO customerDAO = new CustomerDAO();

	private EntityManagerFactory entityManagerFactory;
	
	private CustomerDAO() {
		setup();
	}
	
	public static final CustomerDAO getInstant() {
		return customerDAO;
	}

	public void setup() {
		entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-core");
	}

	public void close() {
		entityManagerFactory.close();
	}

	public void testSave() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Customer cust1 = new Customer("cust1");
		Customer cust2 = new Customer("cust2");

		entityManager.persist(cust1);
		entityManager.persist(cust2);

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void testUpdate(String id, String newUserName) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		Customer cust1 = em.find(Customer.class, Long.parseLong(id));
		cust1.setUserName(newUserName);

		em.getTransaction().commit();
		em.close();
	}
}
