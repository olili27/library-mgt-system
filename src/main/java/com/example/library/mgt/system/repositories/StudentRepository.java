package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);

    List<Student> findAllByName(String name);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    List<Student> findAllByAge(Integer age);
}
