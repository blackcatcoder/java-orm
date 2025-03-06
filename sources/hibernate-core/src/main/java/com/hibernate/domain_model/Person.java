package com.hibernate.domain_model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
	
	private String owner;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
