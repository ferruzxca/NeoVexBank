package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.model.Account;
import com.innovex.neovexbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    // Obtener todas las cuentas
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    // Obtener cuentas por customer_id
    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }

    // Obtener cuenta por id
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear cuenta
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountRepository.save(account));
    }

    // Actualizar cuenta
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account updatedAccount) {
        return accountRepository.findById(id).map(account -> {
            account.setAccountNumber(updatedAccount.getAccountNumber());
            account.setBalance(updatedAccount.getBalance());
            account.setStatus(updatedAccount.getStatus());
            account.setAccountType(updatedAccount.getAccountType());
            account.setCustomer(updatedAccount.getCustomer());
            return ResponseEntity.ok(accountRepository.save(account));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar cuenta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        return accountRepository.findById(id).map(account -> {
            accountRepository.delete(account);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}