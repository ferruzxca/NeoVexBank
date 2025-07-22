package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StatementRepository extends JpaRepository<Statement, Long> {
    List<Statement> findByAccountId(Long accountId);
}