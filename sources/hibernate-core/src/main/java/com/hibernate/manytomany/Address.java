package com.hibernate.manytomany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "address_desc")
	private String addressDesc;

	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PersonAddress> persons = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public List<PersonAddress> getPersons() {
		return persons;
	}

	public void setPersons(List<PersonAddress> persons) {
		this.persons = persons;
	}

}
