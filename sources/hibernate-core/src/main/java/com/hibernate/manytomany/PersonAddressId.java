package com.hibernate.manytomany;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonAddressId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "person_id")
	private Long personId;

	@Column(name = "address_id")
	private Long addressId;
	
	public PersonAddressId() {}

	public PersonAddressId(Long personId, Long addressId) {
		this.personId = personId;
		this.addressId = addressId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		PersonAddressId that = (PersonAddressId) o;
		return Objects.equals(personId, that.personId) && Objects.equals(addressId, that.addressId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personId, addressId);
	}

}
