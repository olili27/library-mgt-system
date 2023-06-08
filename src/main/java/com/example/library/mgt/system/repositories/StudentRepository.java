package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByEmail(String email);

    Optional<Student> findByName(String name);

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
