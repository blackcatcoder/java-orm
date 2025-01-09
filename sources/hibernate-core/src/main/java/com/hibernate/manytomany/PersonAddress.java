package com.hibernate.manytomany;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "person_address")
public class PersonAddress {

	@EmbeddedId
	private PersonAddressId wordSentenceId = new PersonAddressId();

	@ManyToOne
	@MapsId("personId")
	private Person person;

	@ManyToOne
	@MapsId("addressId")
	private Address address;

	@Column(name = "future_field")
	private String futureField;

	public PersonAddress() {}
	
	public PersonAddress(Person person, Address address) {
		this.person = person;
		this.address = address;
	}
	
	public PersonAddressId getWordSentenceId() {
		return wordSentenceId;
	}

	public void setWordSentenceId(PersonAddressId wordSentenceId) {
		this.wordSentenceId = wordSentenceId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFutureField() {
		return futureField;
	}

	public void setFutureField(String futureField) {
		this.futureField = futureField;
	}
}
