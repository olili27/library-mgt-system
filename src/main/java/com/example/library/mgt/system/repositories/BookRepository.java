package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
