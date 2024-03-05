package com.hibernate.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MessageDAO {
	
	private EntityManagerFactory entityManagerFactory;
	
	
	
	public void saveMessage() {
		entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-core");
		
		
		
		
	}
}
