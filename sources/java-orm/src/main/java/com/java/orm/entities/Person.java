package com.java.orm.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<DossierPerson> dossierPersons = new ArrayList<>();

    public Person() {
    }

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DossierPerson> getDossierPersons() {
        return dossierPersons;
    }

    public void setDossierPersons(List<DossierPerson> dossierPersons) {
        this.dossierPersons = dossierPersons;
    }

}
