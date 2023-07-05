package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Integer> {
    List<BookItem> findByTitle(String title);
}
