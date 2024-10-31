package com.apuliadigitalmakers.gestionaleautosalone.repository;

import com.apuliadigitalmakers.gestionaleautosalone.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Long> {


    @Query("SELECT e FROM Departments e WHERE e.deleted IS NULL")
    List<Departments> findNotDeleted();

    @Query("SELECT e FROM Departments e WHERE e.id = :id AND e.deleted IS NULL")
    Optional<Departments> findByIdNotDeleted(@Param("id") Long id);
}
