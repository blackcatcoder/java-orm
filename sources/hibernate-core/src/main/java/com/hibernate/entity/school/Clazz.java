package com.hibernate.entity.school;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.hibernate.annotations.Target;

@Entity(name="clazz")
public class Clazz {

	private Long id;
	
	private String name;
	
	@Embedded
	@Target(Student.class)
	private Member student;
	
	@Embedded
	@Target(Teacher.class)
	private Member teacher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Member getStudent() {
		return student;
	}

	public void setStudent(Member student) {
		this.student = student;
	}

	public Member getTeacher() {
		return teacher;
	}

	public void setTeacher(Member teacher) {
		this.teacher = teacher;
	}
	
}
