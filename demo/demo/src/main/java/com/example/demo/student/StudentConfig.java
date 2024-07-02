package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.SEPTEMBER;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository)
    {
        return args -> {
          Student sharan=  new Student(
                    "Sharan",
                    LocalDate.of(2002, SEPTEMBER,23),
                    "sharan@gmail.com"
          );

            Student sree=  new Student(
                    "sree",
                    LocalDate.of(2000, SEPTEMBER,23),
                    "sree@gmail.com");
            repository.saveAll(
                    List.of(sharan,sree)
            );
        };
    }
}
