package com.hibernate;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class IBaseDAO {
	
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-core-test");
	
	protected EntityManager em;
	
	protected IBaseDAO() {}
	
	protected void begin() {
		close();
		System.out.println("entitymanager is begin");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	protected void commit() {
		if(em != null) {
			System.out.println("transaction is commited");
			em.getTransaction().commit();
		}
	}
	
	protected void rollback() {
		if (em != null && em.getTransaction().isActive()) {
			System.out.println("rollback back action will be applied");
            em.getTransaction().rollback();
        }
	}
	
	protected void close() {
		if (em != null && em.isOpen()) {
			System.out.println("entitymanager is closed");
			em.close();
		}
	}

}
