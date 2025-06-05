package com.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name="employee1")
public class Employee1 {
	
	@Id
	private Long id;
	
	@Column(name="user_name")
	private String userName;
	
	@ColumnTransformer(
			read = "decrypt('AES','00', password)",
			write = "encrypt('AES', '00', ?)"
	)
	@Column(name="password")
	private String password;
	

}
