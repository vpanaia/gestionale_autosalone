package com.apuliadigital.gestionaleautosalone.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {


    @Query("SELECT e FROM Department e WHERE e.deleted IS NULL")
    List<Department> findNotDeleted();

    @Query("SELECT e FROM Department e WHERE e.id = :id AND e.deleted IS NULL")
    Optional<Department> findByIdNotDeleted(@Param("id") Long id);

    List<Department> findByNameContainingIgnoreCase(String name);
}
