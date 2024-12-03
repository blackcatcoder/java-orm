package com.hibernate.persistence.test;

import org.junit.Test;

import com.hibernate.dao.test.BaseDAOTest;
import com.hibernate.persistence.one_to_one.Book;
import com.hibernate.persistence.one_to_one.Manuscript;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OneToOneDaoTest extends BaseDAOTest {
	
	
	@Test
	public void test() {
		
		Book book = new Book();
		book.setName("hibernate");
		em.persist(book);
		
		Book bookPersisted = em.createQuery("from Book", Book.class).getSingleResult();
		
		assertNotNull(bookPersisted);
		System.out.print(bookPersisted.getId());
		
		
		Manuscript script = new Manuscript();
		script.setBook(bookPersisted);
		em.persist(script);
		
		Manuscript scriptPersisted = em.createQuery("from Manuscript", Manuscript.class).getSingleResult();
		
		assertNotNull(scriptPersisted);
		assertEquals(scriptPersisted.getBook().getName(), "hibernate");
		
	}
	
}
