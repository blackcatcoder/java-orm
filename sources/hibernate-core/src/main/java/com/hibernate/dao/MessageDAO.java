package com.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.hibernate.entity.Message;

public class MessageDAO {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-core-test");
	private EntityManager em;

	private static final MessageDAO messageDAO = new MessageDAO();

	private MessageDAO() {
	}

	public static MessageDAO getInstance() {
		return messageDAO;
	}

	// model case for handle entity manager
	private void begin() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	private void close() {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}
	
	private void commit() {
		em.getTransaction().commit();
	}

	public void creteNewMessage(Message message) {
		try {
			begin();
			em.persist(message);
			commit();
		} catch (Exception ex) {
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
		} finally {
			close();
		}
	}

	public void updateMessage(Message message) {
		try {
			begin();
			em.merge(message);
			commit();
		} catch (Exception ex) {
			if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            ex.printStackTrace();
		} finally {
			close();
		}
	}

	public List<Message> findMessageByText(String text) {
		List<Message> messages = null;
		try {
			begin();
			
			messages = em.createQuery("select m from message m where m.text = :text", Message.class)
					.setParameter("text", text)
					.getResultList();
			
			commit();
		} finally {
			close();
		}

		return messages;
	}

	public List<Message> findAll() {
		List<Message> messages = null;
		try {
			begin();
			
			messages = em.createQuery("select m from message m", Message.class).getResultList();
			
			commit();
		} finally {
			close();
		}

		return messages;
	}
}
