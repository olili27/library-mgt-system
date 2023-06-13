package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByName(String name);

    boolean existsByName(String name);
}
