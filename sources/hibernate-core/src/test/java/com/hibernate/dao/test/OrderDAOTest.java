package com.hibernate.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.one_to_many.Order1;
import com.hibernate.one_to_many.OrderItem;

public class OrderDAOTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction et;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("hibernate-core-test");
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
		
		
		// because we have cascade.All in Order1 so we do not need to persist item
		// whenever we persist order it will automatic persists item
		
		OrderItem item = new OrderItem();
		item.setItemName("item name");
		
		Order1 order = new Order1();
		order.setOrderName("order name");
		order.addItem(item);
		
		em.persist(order);
		
		// start test
		List<Order1> orders = em.createQuery("select o from Order1 o", Order1.class).getResultList();
		
		assertEquals(orders.size(), 1);
		System.out.println(orders.get(0).getOrderName());
		
		List<OrderItem> items = orders.get(0).getItems();
	    assertEquals(items.size(), 1);
	    System.out.println(items.get(0).getItemName());


	     // 
	     List<OrderItem> orderItems = em.createQuery("select oi from OrderItem oi", OrderItem.class).getResultList();
	     assertEquals(orderItems.size(), 1);
	    

	    
	}
	
}
