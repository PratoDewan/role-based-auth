package com.spring.securityPractice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserGrantedController {
    @GetMapping("/api-one")
    public ResponseEntity<String> userAPIOne(){
        return new ResponseEntity<>("Hello From User API One", HttpStatus.OK);
    }
    @GetMapping("/api-two")
    public ResponseEntity<String> userAPITwo(){
        return new ResponseEntity<>("Hello From User API Two", HttpStatus.OK);
    }
    @GetMapping("/api-three")
    public ResponseEntity<String> userAPIThree(){
        return new ResponseEntity<>("Hello From User API Three", HttpStatus.OK);
    }
}
