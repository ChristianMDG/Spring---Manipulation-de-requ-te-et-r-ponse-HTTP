package com.Christian.spring_http_handler.service;

import com.Christian.spring_http_handler.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }

    public List<Student> getAllStudents() {
        return students;
    }
}