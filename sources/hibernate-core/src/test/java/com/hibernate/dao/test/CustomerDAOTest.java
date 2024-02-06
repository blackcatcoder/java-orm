package com.hibernate.dao.test;

import junit.framework.TestCase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.hibernate.entity.Customer;
import com.hibernate.entity.Customer_;

public class CustomerDAOTest extends TestCase {

	private EntityManagerFactory entityManagerFactory;

	@Override
	protected void setUp() throws Exception {
		// like discussed with regards to SessionFactory, an EntityManagerFactory is set
		// up once for an application
		// IMPORTANT: notice how the name here matches the name we gave the
		// persistence-unit in persistence.xml!
		entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-core");
	}

	@Override
	protected void tearDown() throws Exception {
		entityManagerFactory.close();
	}

	public void testBasicUsage() {
		// create a couple of events...
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Customer("Cust1"));
		entityManager.persist(new Customer("Cust2"));
		entityManager.persist(new Customer("Cust3"));

		entityManager.getTransaction().commit();
		entityManager.close();

		// now lets pull events from the database and list them
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Customer> result = entityManager.createQuery("from Customer", Customer.class).getResultList();
		for (Customer cust : result) {
			System.out.println("Customer Name: " + cust.getUserName());
		}
		entityManager.getTransaction().commit();
		entityManager.close();

		// find with criteria
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get(Customer_.userName), "Cust1"));

		List<Customer> customers = entityManager.createQuery(criteria).getResultList();
		if (customers != null && !customers.isEmpty()) {
			System.out.println("Criteria Customer Name: " + customers.get(0).getUserName());
		}

		entityManager.getTransaction().commit();
		entityManager.close();
		
		// Test Tuple
		testTuple(entityManager);

	}

	
	// Test Tuple
	public void testTuple(EntityManager entityManager) {
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
		Root<Customer> root = criteria.from(Customer.class);

		Path<Long> idPath = root.get(Customer_.id );
		Path<String> userNamePath = root.get(Customer_.userName);

		criteria.multiselect(idPath, userNamePath);
		criteria.where(builder.equal(root.get(Customer_.userName), "Cust1" ) );

		List<Tuple> tuples = entityManager.createQuery( criteria ).getResultList();
		

		for (Tuple tuple : tuples) {
		    Long id = tuple.get(idPath);
		    String userName = tuple.get(userNamePath);
		    
		    System.out.println("Tuple Customer Id: " + id +" userName: "+userName);
		}
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
