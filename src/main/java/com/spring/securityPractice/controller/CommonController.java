package com.spring.securityPractice.controller;

import com.spring.securityPractice.entity.RoleEntity;
import com.spring.securityPractice.model.ResponseDto;
import com.spring.securityPractice.model.UserDto;
import com.spring.securityPractice.service.RoleService;
import com.spring.securityPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommonController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @PostMapping("/registration")
    public ResponseEntity<ResponseDto> register (@RequestBody UserDto userDto) throws Exception {
        System.out.println();
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @PostMapping("/add-role")
    public ResponseEntity<String> addRole (@RequestBody RoleEntity role) {
        roleService.addRole(role);
        return new ResponseEntity<>("Role added successfully!", HttpStatus.CREATED);
    }
    @GetMapping("/get-roles")
    public ResponseEntity<?> getAllRoles () {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
    @GetMapping("/get-users")
    public ResponseEntity<?> getAllUsers () {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
    @PostMapping("/assign")
    public ResponseEntity<String> assignRoleToUser(@RequestParam Long userId, @RequestParam Integer roleId) {
        roleService.assignRoleToUser(userId, roleId);
        return new ResponseEntity<>("Role assigned successfully!", HttpStatus.OK);
    }
}
