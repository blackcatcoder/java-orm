package com.hibernate.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.hibernate.entity.Customer;
import com.hibernate.entity.Customer_;

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
	
	public List<Customer> findAll(){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		List<Customer> customers = em.createQuery("select m from Customer m", Customer.class).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return customers;
	}
	
	public Customer findWithCriteria() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class );
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select( root );
		criteria.where(builder.equal( root.get(Customer_.userName ), "John Doe"));

		List<Customer> customers = em.createQuery(criteria).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		if(!customers.isEmpty()) {
			return customers.get(0);
		}
		return null;
	}
	
}
