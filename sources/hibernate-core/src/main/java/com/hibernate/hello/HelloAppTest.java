package com.hibernate.hello;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.hibernate.BaseDAOTest;

public class HelloAppTest extends BaseDAOTest {

	@Test
	public void testSave() {
		Message message = new Message();
		message.setText("hello world");

		em.persist(message);

		List<Message> messagees = em.createQuery("select m from Message m", Message.class).getResultList();

		assertEquals(messagees.size(), 1);
		assertEquals(messagees.get(0).getText(), "hello world");
		
	}

}
