package com.hibernate.manytomany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "desc")
	private String desc;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PersonAddress> addresses = new ArrayList<>();

	public void addAddress(Address address) {
		PersonAddress personAddress = new PersonAddress(this, address);

		addresses.add(personAddress);
		address.getPersons().add(personAddress);
	}

	public void removeAddress(Address address) {
		PersonAddress personAddress = new PersonAddress(this, address);

		address.getPersons().remove(personAddress);
		addresses.remove(personAddress);
		personAddress.setPerson(null);
		personAddress.setAddress(null);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<PersonAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<PersonAddress> addresses) {
		this.addresses = addresses;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Person person = (Person) o;
		return Objects.equals(desc, person.desc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc);
	}

}
