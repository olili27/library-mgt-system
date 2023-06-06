package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
