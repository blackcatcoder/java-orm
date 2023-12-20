package com.java.orm.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Dossier {
  @Id private Long id;
  private String name;

  @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<DossierPerson> dossierPersons = new ArrayList<>();

  public void addPerson(Person person) {
    DossierPerson dossierPerson = new DossierPerson();
    dossierPerson.setDossier(this);
    dossierPerson.setPerson(person);

    this.dossierPersons.add(dossierPerson);
  }

  public void removePerson(Person person) {
    DossierPerson dossierPerson =
        this.dossierPersons.stream()
            .filter(dp -> dp.getPerson().getId().equals(person.getId()))
            .findFirst()
            .orElse(null);
    if (dossierPerson != null) {
      this.dossierPersons.remove(dossierPerson);
      person.getDossierPersons().remove(dossierPerson);
    }
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

  @Override
  public String toString() {
    return "Dossier{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", dossierPersonList="
        + dossierPersons
        + '}';
  }
}
