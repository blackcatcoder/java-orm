package com.hibernate.dao.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.hibernate.dao.MessageDAO;
import com.hibernate.entity.Message;

public class MessageDAOTest {

	@Test
	public void saveMessageTest() {
		System.out.println("---> start saveMessageTest");

		MessageDAO messageDAO = MessageDAO.getInstance();
		
		// do the business
		final String text = "hello world";
		
		// action save
		Message message = new Message();
		message.setText(text);
		messageDAO.creteNewMessage(message);
		
		// action find
		List<Message> messages1 = messageDAO.findMessageByText(text);
		assertNotNull(messages1);
		assertEquals(messages1.size(), 1);
		assertEquals(messages1.get(0).getText(), "hello world");
		
		System.out.println("info: "+messages1.get(0));
		
		// action update
		Message updateMessage = messages1.get(0);
		updateMessage.setText("new");
		messageDAO.updateMessage(updateMessage);
		
		// action find
		List<Message> messagesUpdate = messageDAO.findAll();
		assertEquals(messagesUpdate.size(), 1);
		assertEquals(messagesUpdate.get(0).getText(), "new");
		
		// action save
		Message message2 = new Message();
		message2.setText(text);
		messageDAO.creteNewMessage(message2);
		
		// action find
		List<Message> messages2 = messageDAO.findAll();
		assertNotNull(messages2);
		assertEquals(messages2.size(), 2);
		
		
		
		System.out.println("---> end saveMessageTest");
	}
	
}
