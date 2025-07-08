package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Obtiene el balance total de todas las cuentas asociadas al usuario.
     * @param userId ID del usuario cuyo balance queremos consultar.
     * @return JSON con userId y balance.
     */
    @GetMapping("/balance/{userId}")
    public ResponseEntity<Map<String, Object>> getBalance(@PathVariable Long userId) {
        double balance = accountService.getBalanceByUserId(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("balance", balance);
        return ResponseEntity.ok(response);
    }

    // Aquí irían el resto de endpoints (/account/{id}, /account/create, etc.)
}