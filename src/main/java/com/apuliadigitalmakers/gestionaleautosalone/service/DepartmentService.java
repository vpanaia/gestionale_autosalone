package com.apuliadigitalmakers.gestionaleautosalone.service;

import com.apuliadigitalmakers.gestionaleautosalone.model.Departments;
import com.apuliadigitalmakers.gestionaleautosalone.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Departments> findAllDepartments() {
        return departmentRepository.findNotDeleted();
    }

    public Optional<Departments> findDepartmentById(Long id) {
        return departmentRepository.findByIdNotDeleted(id);
    }

    public Departments saveDepartment(Departments department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long id) {
       Departments department = departmentRepository
               .findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Department not found"));

       department.softDelete();
       departmentRepository.save(department);
    }
}
