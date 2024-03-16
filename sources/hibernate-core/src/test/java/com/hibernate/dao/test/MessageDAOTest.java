package com.hibernate.dao.test;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.entity.Message;

public class MessageDAOTest {

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
	public void saveMessageTest() {

		// do the business
		Message message = new Message();
		message.setText("hello world");
		em.persist(message);

		// test
		List<Message> messages = em.createQuery("select m from Message m", Message.class).getResultList();
		assertEquals(messages.size(), 1);
		assertEquals(messages.get(0).getText(), "hello world");
		
		
		List<Message> messages2 = em.createQuery("select new com.hibernate.entity.Message(m.text) from Message m", Message.class).getResultList();
		assertEquals(messages2.size(), 1);
		
		
		//em.unw
		
	}
	
	

}
