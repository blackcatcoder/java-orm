package com.hibernate.entity.domain_model.basic_type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnTransformer;

@Entity(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userName;
    
    
    @Column(name = "password")
    @ColumnTransformer(
            read = "decrypt( 'AES', '00', pswd  )",
            write = "encrypt('AES', '00', ?)"
    )
    private String password;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}