package com.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class BaseDAOTest {
	
	protected static EntityManagerFactory emf;
	protected EntityManager em;
	protected EntityTransaction et;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println(">> setUpBeforeClass");
		
		emf = Persistence.createEntityManagerFactory("hibernate-core");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println(">> setUp");
		
		em = emf.createEntityManager();
		et = em.getTransaction();
		et.begin();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println(">> tearDown");
		
		et.commit();
		em.close();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println(">> tearDownAfterClass");
		
		emf.close();
	}

}
