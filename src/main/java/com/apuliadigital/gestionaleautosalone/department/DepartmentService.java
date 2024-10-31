package com.apuliadigital.gestionaleautosalone.department;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllDepartments() {
        return departmentRepository.findNotDeleted();
    }

    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findByIdNotDeleted(id);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long id) {
       Department department = departmentRepository
               .findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Department not found"));

       department.softDelete();
       departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, Map<String, Object> update) {
        Optional<Department> optionalDepartment = departmentRepository.findByIdNotDeleted(id);

        if (optionalDepartment.isEmpty()) {
            throw new EntityNotFoundException("Department not found");
        }

        Department department = optionalDepartment.get();

        update.forEach((key, value) -> {
            switch (key) {
                case "name":
                    department.setName((String) value);
                    break;
                case "description":
                    department.setDescription((String) value);
                    break;
            }
        });

        return departmentRepository.save(department);
    }

    public List<Department> searchDepartment(String query) {
        return departmentRepository.findByNameContainingIgnoreCase(query);
    }
}
