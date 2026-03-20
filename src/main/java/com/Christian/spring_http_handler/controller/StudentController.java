package com.Christian.spring_http_handler.controller;

import com.Christian.spring_http_handler.entity.Student;
import com.Christian.spring_http_handler.service.StudentService;
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
    public List<Student> getStudentList(){
        return studentService.getStudentList();
    }
}
