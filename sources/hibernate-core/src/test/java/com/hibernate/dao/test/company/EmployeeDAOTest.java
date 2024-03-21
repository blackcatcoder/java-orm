package com.hibernate.dao.test.company;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Test;

import com.hibernate.dao.test.BaseDAOTest;
import com.hibernate.entity.company.Employee;
import com.hibernate.entity.company.Employee_;

public class EmployeeDAOTest extends BaseDAOTest {

	private void dataTest() {
		Employee em1 = new Employee();
		em1.setName("anhvi");

		em.persist(em1);
		em.flush();
	}

	@Test
	public void testSave() {
		dataTest();

		List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();

		assertEquals(employees.size(), 1);
		assertEquals(employees.get(0).getName(), "anhvi");

		// clean up db
		em.createQuery("DELETE FROM Employee").executeUpdate();
	}

	@Test
	public void testGetWithCriteria() {
		dataTest();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<Employee> root = criteria.from(Employee.class);

		criteria.select(root.get(Employee_.name));
		criteria.where(builder.equal(root.get(Employee_.name), "anhvi"));

		List<String> names = em.createQuery(criteria).getResultList();

		assertEquals(names.size(), 1);
		assertEquals(names.get(0), "anhvi");
	}

}
