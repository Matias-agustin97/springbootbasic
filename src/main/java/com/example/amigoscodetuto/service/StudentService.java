package com.example.amigoscodetuto.service;

import com.example.amigoscodetuto.model.Student;
import com.example.amigoscodetuto.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
      Optional<Student> studentByEmail =studentRepository.findStudentByEmail(student.getEmail());
      if(studentByEmail.isPresent()){
          throw new IllegalStateException("Email taken");
      }
      studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exist= studentRepository.existsById(id);
        if (!exist){
            throw  new IllegalStateException("student with id" + id + "does not exist");
        }
        studentRepository.deleteById(id);
    }

    //this annotation allow us to use our setters to change or mutate rows in the database
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student=studentRepository.findById(studentId).orElseThrow(
                ()->new IllegalStateException("student with id"+ studentId+"doest not exist")
        );
        if (name !=null && name.length() > 0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(),email)){
            //check that the emails hasnt been taken
            Optional<Student> studentOptional= studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }
}
