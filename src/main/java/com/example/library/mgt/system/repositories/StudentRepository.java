package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
