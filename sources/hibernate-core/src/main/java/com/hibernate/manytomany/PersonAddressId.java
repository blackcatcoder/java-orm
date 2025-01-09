package com.hibernate.manytomany;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonAddressId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "person_id")
	private Long personId;

	@Column(name = "address_id")
	private Long addressId;

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

}
