package com.example.amigoscodetuto.controller;

import com.example.amigoscodetuto.model.Student;
import com.example.amigoscodetuto.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentservice;

    //autowired automatically instancietes studentservice, so you dont need to add a new keyword
    //but for that you need to the spring that studentservice is a class that needs to be instacieted
    //you do this by adding the annotation @Service or @Component
    @Autowired
    public StudentController(StudentService studentService){
        this.studentservice=studentService;
    }
    @GetMapping
    public List<Student> getStudents(){
        return  studentservice.getStudents();
    }

    @PostMapping
    public  void registerNewStudent(Student student){
        studentservice.addNewStudent(student);
    }

    //esta es la dinamyc route, con la id del usuario para eliminar
    //necesitas usar la anotacion @Pathvariable para acceder a esta ruta dinamica
    @DeleteMapping(path = "{studentId}")
    public  void deleteStudent(@PathVariable("studentId") Long id){
        studentservice.deleteStudent(id);
    }

    @PutMapping(path = "{studentId]")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false)String name,
            @RequestParam(required = false) String email
            ){
        studentservice.updateStudent(studentId,name,email);
    }

}
