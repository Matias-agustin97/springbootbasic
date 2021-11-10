package com.example.amigoscodetuto.repository;

import com.example.amigoscodetuto.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//put your data model on the generic, and the type of id it has
@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
