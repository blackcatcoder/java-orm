package com.hibernate.dao.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.hibernate.entity.Message;

import junit.framework.TestCase;

public class MessageDAOTest extends TestCase {

	private EntityManagerFactory emf;

	@Override
	protected void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("hibernate-core");
	}

	@Override
	protected void tearDown() throws Exception {
		emf.close();
	}

	public void testSaveMessage() {

		// prepare
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		// do the business
		Message message = new Message();
		message.setText("hello world");

		em.persist(message);

		// test
		List<Message> messages = em.createQuery("select m from Message m", Message.class).getResultList();
		// SELECT * from MESSAGE

		for (Message ms : messages) {
			System.out.println(ms.getText());
		}

		assertEquals(messages.size(), 1);
		assertEquals(messages.get(0).getText(), "hello world");

		// clean up
		et.commit();
		em.close();

	}

}
