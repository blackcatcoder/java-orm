package com.java.orm.entities;

import jakarta.persistence.Id;

public class StudentEntity {

  @Id
  private Long id;

  private String name;

}
