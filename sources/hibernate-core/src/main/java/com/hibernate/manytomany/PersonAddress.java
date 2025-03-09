package com.hibernate.manytomany;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person_address")
public class PersonAddress {

	@EmbeddedId
	private PersonAddressId wordSentenceId = new PersonAddressId();

	@ManyToOne
	@JoinColumn(
			name="person_id",
			insertable = false,
			updatable = false
	)
	private Person person;

	@ManyToOne
	@JoinColumn(
			name="address_id",
			insertable = false,
			updatable = false
	)
	private Address address;

	@Column(name = "future_field")
	private String futureField;

	public PersonAddress() {
	}

	public PersonAddress(Person person, Address address) {
		this.person = person;
		this.address = address;
		this.futureField = "future field";
		
		this.wordSentenceId = new PersonAddressId(person.getId(), address.getId());
		person.getPersonAddresses().add(this);
		address.getPersonAddresses().add(this);
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonAddress that = (PersonAddress) o;
		return Objects.equals(person, that.person) && Objects.equals(address, that.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(person, address);
	}
}
