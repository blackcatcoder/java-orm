package com.java.orm.repository;

import com.java.orm.entities.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends CrudRepository<StudentEntity, Long> {

}
