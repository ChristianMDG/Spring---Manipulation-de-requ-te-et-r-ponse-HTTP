package com.Christian.spring_http_handler.controller;

import com.Christian.spring_http_handler.entity.Student;
import com.Christian.spring_http_handler.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private StudentService studentService =  new StudentService();

    @GetMapping("/welcome")
    public String welcome(@RequestParam String name){
        return "Welcome "+name;
    }

    @PostMapping("/students")
    public String addStudent(@RequestBody List<Student> student){
       studentService.addStudent(student);
       return studentService.getStudentName();
    }
    @GetMapping("/students")
    public ResponseEntity<String> getStudentName(@RequestHeader(HttpHeaders.ACCEPT)  String acceptHeader){
        if("text/plain".equalsIgnoreCase(acceptHeader)){
           return ResponseEntity.ok(studentService.getStudentName());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid Accept");
        }
    }
}
