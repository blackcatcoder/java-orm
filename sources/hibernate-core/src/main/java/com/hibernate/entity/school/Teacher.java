package com.hibernate.entity.school;

import javax.persistence.Embeddable;

@Embeddable
public class Teacher implements Member {
	
	private String teacherName;

	public String getName() {
		return teacherName;
	}


}
