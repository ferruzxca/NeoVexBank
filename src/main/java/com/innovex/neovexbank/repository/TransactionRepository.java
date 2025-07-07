package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}