package com.Christian.spring_http_handler.entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String reference;
    private String firstName;
    private String lastName;
    private int  age;
}
