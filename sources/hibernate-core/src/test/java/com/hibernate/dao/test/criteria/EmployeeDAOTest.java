package com.hibernate.dao.test.criteria;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Test;

import com.hibernate.criteria.Employee;
import com.hibernate.dao.test.BaseDAOTest;
import com.hibernate.entity.criteria.Employee_;

public class EmployeeDAOTest extends BaseDAOTest {

	private void initDataTest() {
		Employee em1 = new Employee();
		em1.setName("anhvi");
		em1.setAge(10);

		em.persist(em1);
		em.flush();
	}
	
	private void cleanUpDataTest() {
		em.createQuery("DELETE FROM Employee").executeUpdate();
	}

	@Test
	public void testSave() {
		initDataTest();

		List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();

		assertEquals(employees.size(), 1);
		assertEquals(employees.get(0).getName(), "anhvi");

		cleanUpDataTest();
	}

	@Test
	public void testGetEntityWithCriteria() {
		initDataTest();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root);
		criteria.where(builder.equal(root.get(Employee_.name), "anhvi"));

		List<Employee> names = em.createQuery(criteria).getResultList();

		assertEquals(names.size(), 1);
		assertEquals(names.get(0).getName(), "anhvi");
		assertEquals(names.get(0).getAge(), 10);
		
		cleanUpDataTest();
	}
	
	@Test
	public void getFieldWithCriteria() {
		initDataTest();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		criteria.select(root.get(Employee_.name));
		criteria.where(builder.equal(root.get(Employee_.name), "anhvi"));

		List<String> names = em.createQuery(criteria).getResultList();

		assertEquals(names.size(), 1);
		assertEquals(names.get(0), "anhvi");
		
		
		cleanUpDataTest();
	}
	
	@Test
	public void getTupleWithCriteria() {
		initDataTest();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
		Root<Employee> root = criteria.from(Employee.class);
		
		Path<Long> idPath = root.get( Employee_.id );
		Path<String> namePath = root.get( Employee_.name);
		Path<Integer> agePath = root.get( Employee_.age);
		
		criteria.multiselect(idPath, namePath, agePath);
		criteria.where(builder.equal(root.get(Employee_.name), "anhvi"));

		List<Tuple> employees = em.createQuery(criteria).getResultList();
		
		for ( Tuple tuple : employees ) {
		    Long id = (Long) tuple.get( 0 );
		    String name = (String) tuple.get( 1 );
		    int age = (int) tuple.get( 2 );
		    System.out.print("id: "+id+" name: "+name+" age: "+age);
		}

		assertEquals(employees.size(), 1);
		assertEquals(employees.get(0).get(1), "anhvi");
		assertEquals(employees.get(0).get(2), 10);
		
		cleanUpDataTest();
	}

}
