package com.Christian.spring_http_handler.controller;

import com.Christian.spring_http_handler.entity.Student;
import com.Christian.spring_http_handler.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
 private StudentService studentService =  new StudentService();
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam(value = "name", required = false) String name){
        if (name==null || name.trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parameter 'name' is required");
        }
        else {
            String reponse = "Welcome "+name+"!";
            return ResponseEntity.status(HttpStatus.OK).body(reponse);
        }
    }

    @PostMapping("/students")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students){
        try{
            List<Student> savedStudents = studentService.addStudent(students);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudents);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
