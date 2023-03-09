package com.java.orm;

import com.java.orm.entities.StudentEntity;
import com.java.orm.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class JavaOrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaOrmApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {
			studentRepository.save(new StudentEntity(1l, "vinv.2491@gmail.com"));
		};
	}

}
