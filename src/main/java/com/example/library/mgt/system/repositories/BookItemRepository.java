package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookItemRepository extends JpaRepository<BookItem, Integer> {
}
