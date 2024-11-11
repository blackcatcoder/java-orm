package com.hibernate.persistence.test;

import org.junit.Test;

import com.hibernate.dao.test.BaseDAOTest;
import com.hibernate.persistence.one_to_one.Book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OneToOneDaoTest extends BaseDAOTest {
	
	
	@Test
	public void test() {
		
		Book book = new Book();
		em.persist(book);
		
		Book bookFound = em.createQuery("from Book", Book.class).getSingleResult();
		
		
		assertNotNull(bookFound);
		System.out.print(bookFound.getId());
		
		
	}
	
}
