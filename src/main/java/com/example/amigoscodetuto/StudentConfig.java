package com.example.amigoscodetuto;

import com.example.amigoscodetuto.model.Student;
import com.example.amigoscodetuto.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return  args -> {
            Student matias =new Student(
                    "Matias",
                    "MAtias@gmail.com",
                    LocalDate.of(1997, Month.MARCH,20));
            Student fabiana = new Student(
                    "Fabiana",
                    "fabi@gmail.com",
                    LocalDate.of(2000,Month.AUGUST,8)
            );
            repository.saveAll(List.of(matias,fabiana));
        };
    };


}
