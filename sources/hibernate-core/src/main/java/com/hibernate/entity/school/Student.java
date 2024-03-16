package com.hibernate.entity.school;

import javax.persistence.Embeddable;

@Embeddable
public class Student implements Member {
	
	private String studentName;

	public String getName() {
		return studentName;
	}

}
