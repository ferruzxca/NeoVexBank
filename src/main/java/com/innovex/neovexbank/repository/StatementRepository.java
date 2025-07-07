package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long> {
}