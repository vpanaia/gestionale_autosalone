package com.apuliadigital.gestionaleautosalone.user;

import com.apuliadigital.gestionaleautosalone.employee.Employee;
import com.apuliadigital.gestionaleautosalone.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final String notFoundMessage = "User not found";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
       return userRepository.findByUsername(username);
    }

    public User saveUser(UserRequestDTO userRequestDTO) {
        Employee employee = employeeRepository.findById(userRequestDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        User newUser = new User();
        newUser.setUsername(userRequestDTO.getUsername());
        newUser.setPassword(userRequestDTO.getPassword());
        newUser.setEmployee(employee);

        return userRepository.save(newUser);
    }
}
