package com.example.library.mgt.system.repositories;

import com.example.library.mgt.system.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
