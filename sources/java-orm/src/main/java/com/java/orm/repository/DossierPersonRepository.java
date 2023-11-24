package com.java.orm.repository;

import com.java.orm.entities.Dossier;
import com.java.orm.entities.DossierPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierPersonRepository extends JpaRepository<DossierPerson, Long> {
}
