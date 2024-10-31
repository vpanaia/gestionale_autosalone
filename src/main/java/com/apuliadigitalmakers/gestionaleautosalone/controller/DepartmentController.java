package com.apuliadigitalmakers.gestionaleautosalone.controller;

import com.apuliadigitalmakers.gestionaleautosalone.model.Departments;
import com.apuliadigitalmakers.gestionaleautosalone.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<Departments> getAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @GetMapping("/{id}")
    public Optional<Departments> getDepartment(@PathVariable Long id) {
        return departmentService.findDepartmentById(id);
    }

    @PostMapping("/add")
    public Departments addDepartment(@RequestBody Departments department) {
        return departmentService.saveDepartment(department);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

}
