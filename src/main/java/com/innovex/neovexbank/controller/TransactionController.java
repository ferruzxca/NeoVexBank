package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.dto.DepositRequest;
import com.innovex.neovexbank.dto.WithdrawRequest;
import com.innovex.neovexbank.dto.TransferRequest;
import com.innovex.neovexbank.model.Transaction;
import com.innovex.neovexbank.service.TransactionService;
import com.innovex.neovexbank.utils.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Respuesta> deposit(@RequestBody DepositRequest request) {
        Respuesta res = transactionService.deposit(request);
        return ResponseEntity.status(res.isEstado() ? 200 : 400).body(res);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Respuesta> withdraw(@RequestBody WithdrawRequest request) {
        Respuesta res = transactionService.withdraw(request);
        return ResponseEntity.status(res.isEstado() ? 200 : 400).body(res);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Respuesta> transfer(@RequestBody TransferRequest request) {
        Respuesta res = transactionService.transfer(request);
        return ResponseEntity.status(res.isEstado() ? 200 : 400).body(res);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountId) {
        List<Transaction> list = transactionService.getTransactions(accountId);
        return ResponseEntity.ok(list);
    }
}