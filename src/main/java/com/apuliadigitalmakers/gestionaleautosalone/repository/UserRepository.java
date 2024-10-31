package com.apuliadigitalmakers.gestionaleautosalone.repository;

import com.apuliadigitalmakers.gestionaleautosalone.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
}
