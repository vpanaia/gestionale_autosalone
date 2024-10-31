package com.apuliadigital.gestionaleautosalone.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.findAllDepartments();
    }

    @GetMapping("/{id}")
    public Optional<Department> getDepartment(@PathVariable Long id) {
        return departmentService.findDepartmentById(id);
    }

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PatchMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Map<String, Object> update) {
       return departmentService.updateDepartment(id, update);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

   @GetMapping("/search")
   public List<Department> searchDepartment(@RequestParam String query) {
        return departmentService.searchDepartment(query);
   }

}
