package com.Christian.spring_http_handler.service;

import com.Christian.spring_http_handler.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private List<Student> studentList = new ArrayList<>();
    public List<Student> addStudent(List<Student> student) {
        studentList.addAll(student);
        return studentList;
    }
    public String getStudentName() {
        return studentList.stream().map(Student::getFirstName).collect(Collectors.joining(","));
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
