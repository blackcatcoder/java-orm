package com.java.orm;

import com.java.orm.entities.Dossier;
import com.java.orm.entities.DossierPerson;
import com.java.orm.entities.Person;
import com.java.orm.entities.StudentEntity;
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
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, DossierRepository dossierRepository, PersonRepository personRepository){
		return args -> {
			studentRepository.save(new StudentEntity(1l, "vinv.2491@gmail.com"));

			List<DossierPerson> dossierPersonList = new ArrayList<>();
			DossierPerson dossierPerson = new DossierPerson();
			dossierPerson.setId(3l);
			dossierPerson.setStatus("active");


			Person p1 = new Person();
			p1.setId(2l);
			p1.setName("person name 1");
			//p1.setDossierPersonList(dossierPersonList);
			personRepository.save(p1);

			Dossier d1 = new Dossier();
			d1.setId(1l);
			d1.setName("dossier name 1");
			dossierPerson.setDossier(d1);
			dossierPerson.setPerson(p1);
			dossierPersonList.add(dossierPerson);
			d1.setDossierPersonList(dossierPersonList);
			dossierRepository.save(d1);

			List<DossierPerson> dossierPersonList2 = new ArrayList<>();
			DossierPerson dossierPerson2 = new DossierPerson();
			dossierPerson2.setId(4l);
			dossierPerson2.setStatus("inactive");

			Dossier d2 = new Dossier();
			d2.setId(5l);
			d2.setName("dossier name 2");

			dossierPerson2.setDossier(d2);
			dossierPerson2.setPerson(p1);
			dossierPersonList2.add(dossierPerson2);
			d2.setDossierPersonList(dossierPersonList2);
			dossierRepository.save(d2);

		};
	}

}
