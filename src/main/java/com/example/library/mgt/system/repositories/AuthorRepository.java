package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
