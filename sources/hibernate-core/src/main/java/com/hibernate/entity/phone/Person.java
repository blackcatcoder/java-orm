package com.hibernate.entity.phone;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "Person")
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Phone> phones = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
}
