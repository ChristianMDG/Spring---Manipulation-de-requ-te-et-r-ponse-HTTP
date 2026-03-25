package com.Christian.spring_http_handler.controller;

import com.Christian.spring_http_handler.entity.Student;
import com.Christian.spring_http_handler.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String acceptHeader) {
        try {
            if (acceptHeader == null || acceptHeader.trim().isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST).body("Header Accept manquant");
            }
            List<Student> students = studentService.getAllStudents();

            if (acceptHeader.equalsIgnoreCase("application/json")) {
                return ResponseEntity.status(HttpStatus.OK).body(students);
            }

            if (acceptHeader.equalsIgnoreCase("text/plain")) {
                String names = students.stream()
                        .map(Student::getFirstName)
                        .collect(Collectors.joining(","));

                return ResponseEntity
                        .ok(names);
            }
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body("Format non supporté");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne serveur");
        }
    }
}