package com.apuliadigital.gestionaleautosalone.department;

import com.apuliadigital.gestionaleautosalone.common.ResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/departments")
@Tag(name = "Departments", description = "List of Departments endpoints")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Get the list of all departments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Department.class))) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
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

       if (query.length() < 3) {
           return ResponseBuilder.badRequest("Required at least 3 characters");
       }

        List<Department> searchResults = departmentService.searchDepartment(query);
        if (searchResults.isEmpty()) {
            return ResponseBuilder.notFound("Search has no results");
        }

        return ResponseBuilder.searchResults(searchResults, searchResults.size());
   }

}
