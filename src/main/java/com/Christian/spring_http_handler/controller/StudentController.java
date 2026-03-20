package com.Christian.spring_http_handler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

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
}
