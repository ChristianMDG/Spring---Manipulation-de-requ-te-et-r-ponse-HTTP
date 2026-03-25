package com.Christian.spring_http_handler.validator;

import com.Christian.spring_http_handler.entity.Student;
import com.Christian.spring_http_handler.exception.BadRequestException;

import java.util.List;

public class StudentValidator {

    public void validate(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new BadRequestException("The list of students should not be null or empty");
        }

        for (Student student : students) {
            if (student.getReference() == null || student.getReference().isBlank()) {
                throw new BadRequestException("Student.reference should not be null or empty");
            }
            if (student.getFirstName() == null || student.getFirstName().isBlank()) {
                throw new BadRequestException("Student.firstName should not be null or empty");
            }
            if (student.getLastName() == null || student.getLastName().isBlank()) {
                throw new BadRequestException("Student.lastName should not be null or empty");
            }
        }
    }
}