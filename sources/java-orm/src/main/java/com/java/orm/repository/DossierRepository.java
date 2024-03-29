package com.java.orm.repository;

import com.java.orm.entities.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
}

/*

SELECT * FROM DOSSIER ;
SELECT * FROM DOSSIER_PERSON  ;
SELECT * FROM PERSON  ;

delete from Person where id = 31;

 */