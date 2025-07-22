package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.model.AccountType;
import com.innovex.neovexbank.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-type")
public class AccountTypeController {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @GetMapping
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
        return ResponseEntity.ok(accountTypeRepository.findAll());
    }
}