package com.spring.securityPractice.service;

import com.spring.securityPractice.entity.RoleEntity;
import com.spring.securityPractice.entity.UserEntity;
import com.spring.securityPractice.repository.RoleRepository;
import com.spring.securityPractice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfo;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    public void addRole(RoleEntity role){
        roleRepository.save(role);
    }
    public List<RoleEntity> getAllRoles(){
        return roleRepository.findAll();
    }
    public void assignRoleToUser(Long id, Integer roleId) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        Optional<RoleEntity> roleOptional = roleRepository.findById(roleId);

        if (userOptional.isPresent() && roleOptional.isPresent()) {
            UserEntity user = userOptional.get();
            RoleEntity role = roleOptional.get();

            user.getRoles().add(role);
            userRepository.save(user);
        }
    }
}
