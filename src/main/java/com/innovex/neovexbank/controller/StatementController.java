package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.model.Statement;
import com.innovex.neovexbank.service.StatementService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/statements")
public class StatementController {

    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    // GET: /statements/account/{accountId}
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Statement>> getStatementsByAccount(@PathVariable Long accountId) {
        List<Statement> statements = statementService.getStatementsByAccount(accountId);
        return ResponseEntity.ok(statements);
    }

    // POST: /statements
    @PostMapping
    public ResponseEntity<Statement> createStatement(@RequestBody Statement statement) {
        Statement saved = statementService.saveStatement(statement);
        return ResponseEntity.ok(saved);
    }
}