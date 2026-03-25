package com.Christian.spring_http_handler.validator;

import com.Christian.spring_http_handler.entity.Student;
import com.Christian.spring_http_handler.exception.BadRequestException;

import java.util.List;

public class StudentValidator {

    public void validate(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new BadRequestException("La liste d'étudiants ne peut pas être vide");
        }

        for (Student student : students) {
            if (student.getReference() == null || student.getReference().isBlank()) {
                throw new BadRequestException("Student.reference ne peut pas être nul ou vide");
            }
            if (student.getFirstName() == null || student.getFirstName().isBlank()) {
                throw new BadRequestException("Student.firstName ne peut pas être nul ou vide");
            }
            if (student.getLastName() == null || student.getLastName().isBlank()) {
                throw new BadRequestException("Student.lastName ne peut pas être nul ou vide");
            }
        }
    }
}