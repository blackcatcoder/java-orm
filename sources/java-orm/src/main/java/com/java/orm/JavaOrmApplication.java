package com.java.orm;

import com.java.orm.entities.Dossier;
import com.java.orm.entities.DossierPerson;
import com.java.orm.entities.Person;
import com.java.orm.repository.DossierRepository;
import com.java.orm.repository.PersonRepository;
import com.java.orm.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Configuration
public class JavaOrmApplication {

  public static void main(String[] args) {
    SpringApplication.run(JavaOrmApplication.class, args);
  }

  @Bean
  CommandLineRunner commandLineRunner(
      StudentRepository studentRepository,
      DossierRepository dossierRepository,
      PersonRepository personRepository) {
    return args -> {
      // studentRepository.save(new StudentEntity(1l, "vinv.2491@gmail.com"));

      List<DossierPerson> dpl = new ArrayList<>();
      DossierPerson dp1 = new DossierPerson();
      dp1.setId(11l);
      dp1.setStatus("active1");
      DossierPerson dp2 = new DossierPerson();
      dp2.setId(12l);
      dp2.setStatus("active2");

      Person p1 = new Person();
      p1.setId(31l);
      p1.setName("person name 1");
      p1 = personRepository.save(p1);

      Person p2 = new Person();
      p2.setId(32l);
      p2.setName("person name 2");
      p2 = personRepository.save(p2);

      Dossier d1 = new Dossier();
      d1.setId(1l);
      d1.setName("dossier name 1");

      dp1.setDossier(d1);
      dp1.setPerson(p1);
      dpl.add(dp1);
      dp2.setDossier(d1);
      dp2.setPerson(p2);
      dpl.add(dp2);

      d1.setDossierPersons(dpl);
      d1 = dossierRepository.save(d1);
    };
  }
}
