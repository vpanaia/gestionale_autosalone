package com.apuliadigital.gestionaleautosalone.department;

import com.apuliadigital.gestionaleautosalone.common.ResponseBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDepartments() {
        try {
            List<Department> departments = departmentService.findAllDepartments();

            if (departments.isEmpty()) {
                return ResponseBuilder.notFound("Departments not found");
            } else {
                return ResponseBuilder.success(departments);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.error();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id) {
        try {
            return ResponseBuilder.success(departmentService.findDepartmentById(id));
        }
        catch (EntityNotFoundException e) {
            return ResponseBuilder.notFound(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.error();
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addDepartment(@RequestBody Department department) {
        try {
            return ResponseBuilder.success(departmentService.saveDepartment(department));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.error();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @RequestBody Map<String, Object> update) {
        try {
            return ResponseBuilder.success(departmentService.updateDepartment(id, update));
        }
        catch (EntityNotFoundException e) {
            return ResponseBuilder.notFound(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.error();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {

        try {
            departmentService.deleteDepartment(id);
            return ResponseBuilder.deleted("Department deleted successfully");
        }
        catch (EntityNotFoundException e) {
            return ResponseBuilder.notFound(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.error();
        }
    }

   @GetMapping("/search")
   public ResponseEntity<?> searchDepartment(@RequestParam String query) {
        List<Department> searchResults = departmentService.searchDepartment(query);

        if (searchResults.isEmpty()) {
            return ResponseBuilder.notFound("Search has no results");
        }

        return ResponseBuilder.success(searchResults);
   }

}
