package com.java.orm.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dossier {
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DossierPerson> dossierPersonList;

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

    public List<DossierPerson> getDossierPersonList() {
        return dossierPersonList;
    }

    public void setDossierPersonList(List<DossierPerson> dossierPersonList) {
        this.dossierPersonList = dossierPersonList;
    }

    @Override
    public String toString() {
        return "Dossier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dossierPersonList=" + dossierPersonList +
                '}';
    }
}
