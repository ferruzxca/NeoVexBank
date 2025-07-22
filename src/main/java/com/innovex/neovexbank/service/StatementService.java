package com.innovex.neovexbank.service;

import com.innovex.neovexbank.model.Statement;
import com.innovex.neovexbank.repository.StatementRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatementService {
    private final StatementRepository statementRepository;

    public StatementService(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    public List<Statement> getStatementsByAccount(Long accountId) {
        return statementRepository.findByAccountId(accountId);
    }

    public Statement saveStatement(Statement statement) {
        return statementRepository.save(statement);
    }
}