package com.spring.securityPractice.service;


import com.spring.securityPractice.entity.UserEntity;
import com.spring.securityPractice.model.ResponseDto;
import com.spring.securityPractice.model.UserDto;

import java.util.List;

public interface UserService {
    ResponseDto createUser(UserDto user) throws Exception;
    UserDto getUser(String email);

    UserDto getUserByUserId(String id) throws Exception;
    List<UserEntity> findAllUsers();

}