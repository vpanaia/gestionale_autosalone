package com.apuliadigitalmakers.gestionaleautosalone.service;

import com.apuliadigitalmakers.gestionaleautosalone.model.Users;
import com.apuliadigitalmakers.gestionaleautosalone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> findAllDepartments() {
        return userRepository.findAll();
    }

    public Optional<Users> findDepartmentById(Long id) {
        return userRepository.findById(id);
    }

    public Users saveDepartment(Users user) {
        return userRepository.save(user);
    }
}
