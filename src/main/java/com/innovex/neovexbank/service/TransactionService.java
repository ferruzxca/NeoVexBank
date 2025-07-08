package com.innovex.neovexbank.service;

import com.innovex.neovexbank.dto.TransferRequest;
import com.innovex.neovexbank.model.Account;
import com.innovex.neovexbank.model.Statement;
import com.innovex.neovexbank.repository.AccountRepository;
import com.innovex.neovexbank.repository.StatementRepository;
import com.innovex.neovexbank.utils.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private StatementRepository statementRepo;

    public Respuesta realizarTransferencia(TransferRequest request) {
        if (request.getAmount() > 20000) {
            return new Respuesta("No se permiten transferencias mayores a $20,000", false);
        }

        Account origen = accountRepo.findById(request.getFromAccountId()).orElse(null);
        Account destino = accountRepo.findById(request.getToAccountId()).orElse(null);

        if (origen == null || destino == null) {
            return new Respuesta("Cuenta origen o destino no encontrada", false);
        }

        if (origen.getBalance() < request.getAmount()) {
            return new Respuesta("Saldo insuficiente", false);
        }

        // Actualiza saldos
        origen.setBalance(origen.getBalance() - request.getAmount());
        destino.setBalance(destino.getBalance() + request.getAmount());

        accountRepo.save(origen);
        accountRepo.save(destino);

        // Registrar en estados de cuenta
        Statement s1 = new Statement(origen, -request.getAmount(), "Transferencia enviada a cuenta " + destino.getId());
        Statement s2 = new Statement(destino, request.getAmount(), "Transferencia recibida de cuenta " + origen.getId());

        statementRepo.save(s1);
        statementRepo.save(s2);

        return new Respuesta("Transferencia realizada con éxito", true);
    }
}