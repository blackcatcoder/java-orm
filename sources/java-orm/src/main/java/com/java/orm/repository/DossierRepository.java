package com.java.orm.repository;

import com.java.orm.entities.Dossier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierRepository extends CrudRepository<Dossier, Long> {
}
