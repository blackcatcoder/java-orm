package com.java.orm.controller;

import com.java.orm.entities.Person;
import com.java.orm.entities.Dossier;
import com.java.orm.entities.DossierPerson;
import com.java.orm.repository.DossierPersonRepository;
import com.java.orm.repository.DossierRepository;
import com.java.orm.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IndexController {

  private final DossierRepository dossierRepository;
  private final PersonRepository personRepository;

  private final DossierPersonRepository dossierPersonRepository;

  public IndexController(
      DossierRepository dossierRepository,
      PersonRepository personRepository,
      DossierPersonRepository dossierPersonRepository) {
    this.dossierRepository = dossierRepository;
    this.personRepository = personRepository;
    this.dossierPersonRepository = dossierPersonRepository;
  }

  @GetMapping("index")
  public String getIndex() {
    return "index";
  }

  @GetMapping("dossier")
  public String getDossier() {
    Dossier rs = dossierRepository.findById(1l).orElse(null);
    List<DossierPerson> dossierPersonList = rs.getDossierPersons();
    System.out.println(dossierPersonList.size());
    Person p = dossierPersonList.get(0).getPerson();
    System.out.println(p.getDossierPersons().size());

    Dossier active =
        p.getDossierPersons().stream()
            .filter(d -> d.getStatus().equals("inactive"))
            .map(d -> d.getDossier())
            .findFirst()
            .orElse(null);
    System.out.println(active);

    return rs != null ? rs.toString() : "";
  }

  @GetMapping("person")
  public String getPerson() {
    Person p = personRepository.findById(2l).orElse(null);
    System.out.println(p.getDossierPersons().size());
    System.out.println("");
    return "lala";
  }

  @GetMapping("person/getAll")
  public List<Person> getAllPerson() {
    List<Person> persons = personRepository.findAll();
    return persons.stream()
        .map(p -> new Person(p.getId(), p.getName()))
        .collect(Collectors.toList());
  }

  @GetMapping("person/{id}")
  public void deletePerson(@PathVariable Long id) {
    personRepository.deleteById(id);
  }

  @GetMapping("dossier/{dossierId}/person/{personId}")
  public void removePersonFromDossier(@PathVariable Long dossierId, @PathVariable Long personId) {
    Dossier dossier = dossierRepository.findById(dossierId).orElse(null);
    Person person = personRepository.findById(personId).orElse(null);

    if (dossier != null && person != null) {
      DossierPerson dossierPerson = findDossierPerson(dossier, person);
      if (dossierPerson != null) {
        dossier.getDossierPersons().remove(dossierPerson);
        dossierPersonRepository.delete(dossierPerson);
      }
    }
  }

  private DossierPerson findDossierPerson(Dossier dossier, Person person) {
    for (DossierPerson dp : dossier.getDossierPersons()) {
      if (dp.getPerson().equals(person)) {
        return dp;
      }
    }
    return null;
  }
}
