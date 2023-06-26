package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByEmail(String email);

    List<Student> findAllByName(String name);

    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE CONCAT('%', LOWER(:studentName), '%')")
    List<Student> findByNameContaining(@Param("studentName") String studentName);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE LOWER(s.name) LIKE CONCAT('%', LOWER(:studentName), '%')")
    boolean existsByNameContaining(@Param("studentName") String studentName);

    boolean existsByEmail(String email);

    boolean existsByName(String name);  

    List<Student> findAllByAge(Integer age);
}
