package com.hibernate.dao.test.domain_model;

import com.hibernate.dao.test.BaseDAOTest;
import com.hibernate.domain_model.Contact;
import com.hibernate.domain_model.Name;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class DomainModelTest extends BaseDAOTest{
	
	@Test
	public void testSaveData() {
		Contact ct = new Contact();
		ct.setNotes("anhvi");
		ct.setOwner("owner");
		
		Name name = new Name();
		name.setFirst("nong");
		name.setLast("Vi");
		name.setMiddle("van");
		
		ct.setName(name);
		
		em.persist(ct);
		em.flush();
		
		List<Contact> rs = em.createQuery("select c from Contact c", Contact.class).getResultList();
		
		assertEquals(rs.size(), 1);
		assertEquals(rs.get(0).getNotes(), "anhvi");
		assertEquals(rs.get(0).getName().getFirst(), "nong");
		
		assertEquals(rs.get(0).getOwner(), "owner");
		
	}

}
