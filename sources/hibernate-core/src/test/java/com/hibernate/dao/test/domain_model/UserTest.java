package com.hibernate.dao.test.domain_model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.hibernate.dao.test.BaseDAOTest;
import com.hibernate.entity.domain_model.basic_type.User;

public class UserTest extends BaseDAOTest {
	
	@Test
	public void testSaveData() {
		User user = new User();
		user.setUserName("nvvi");
		user.setPassword("pass");
		
		em.persist(user);
		em.flush();
		
		List<User> rs = em.createQuery("select c from User c", User.class).getResultList();
		
		for(User temp : rs) {
			System.out.println(temp.getUserName()+"-"+temp.getPassword());
		}
		
		assertEquals(rs.size(), 1);
	}

	
	
	
}
