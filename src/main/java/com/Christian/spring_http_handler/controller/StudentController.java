package com.Christian.spring_http_handler.controller;

import com.Christian.spring_http_handler.entity.Student;
import com.Christian.spring_http_handler.exception.BadRequestException;
import com.Christian.spring_http_handler.service.StudentService;
import com.Christian.spring_http_handler.validator.StudentValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentController {

    private final StudentService studentService = new StudentService();
    private final StudentValidator studentValidator = new StudentValidator();

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(
            @RequestParam(value = "name", required = false) String name) {
        if (name == null || name.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("The paramaters name is required");
        }
        return ResponseEntity.ok("Welcome " + name + "!");
    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudents(@RequestBody List<Student> students) {
        try {
            studentValidator.validate(students);
            List<Student> saved = studentService.addStudents(students);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(
            @RequestHeader(value = "Accept", required = false) String acceptHeader) {
        if (acceptHeader == null || acceptHeader.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Header Accept is missing");
        }

        List<Student> students = studentService.getAllStudents();

        if (acceptHeader.equalsIgnoreCase("application/json")) {
            return ResponseEntity.ok(students);
        }

        if (acceptHeader.equalsIgnoreCase("text/plain")) {
            String names = students.stream()
                    .map(Student::getFirstName)
                    .collect(Collectors.joining(","));
            return ResponseEntity.ok(names);
        }

        return ResponseEntity
                .status(HttpStatus.NOT_IMPLEMENTED)
                .body("Format not supported");
    }
}