package com.spring.securityPractice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminGrantedController {
    @GetMapping("/api-one")
    public ResponseEntity<String> adminAPIOne(){
        return new ResponseEntity<>("Hello From Admin API One", HttpStatus.OK);
    }
    @GetMapping("/api-two")
    public ResponseEntity<String> adminAPITwo(){
        return new ResponseEntity<>("Hello From Admin API Two", HttpStatus.OK);
    }
    @GetMapping("/api-three")
    public ResponseEntity<String> adminAPIThree(){
        return new ResponseEntity<>("Hello From Admin API Three", HttpStatus.OK);
    }
}
