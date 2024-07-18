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

import com.hibernate.entity.enums.Trip;
import com.hibernate.entity.enums.Vehicle;

public class TripDAOTest {
	
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
		
		Trip trip = new Trip();
		trip.setVehicle(Vehicle.BUS);
		
		em.persist(trip);
		
		List<Trip> trips = em.createQuery("select t from Trip t", Trip.class).getResultList();
		assertEquals(trips.size(), 1);
		assertEquals(trips.get(0).getVehicle(), Vehicle.BUS);
	}
}
