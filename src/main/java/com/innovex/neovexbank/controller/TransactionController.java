package com.innovex.neovexbank.controller;

import com.innovex.neovexbank.dto.TransferRequest;
import com.innovex.neovexbank.service.TransactionService;
import com.innovex.neovexbank.utils.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public Respuesta transfer(@RequestBody TransferRequest request) {
        return transactionService.realizarTransferencia(request);
    }
}