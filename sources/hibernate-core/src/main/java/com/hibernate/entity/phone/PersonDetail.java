package com.hibernate.entity.phone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "person_detail")
public class PersonDetail {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "person_details")
	private String personDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPersonDetails() {
		return personDetails;
	}

	public void setPersonDetails(String personDetails) {
		this.personDetails = personDetails;
	}

}
