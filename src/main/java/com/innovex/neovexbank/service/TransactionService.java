package com.innovex.neovexbank.service;

import com.innovex.neovexbank.dto.DepositRequest;
import com.innovex.neovexbank.dto.TransferRequest;
import com.innovex.neovexbank.dto.WithdrawRequest;
import com.innovex.neovexbank.model.Account;
import com.innovex.neovexbank.model.Transaction;
import com.innovex.neovexbank.repository.AccountRepository;
import com.innovex.neovexbank.repository.TransactionRepository;
import com.innovex.neovexbank.utils.Respuesta;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    public Respuesta deposit(DepositRequest request) {
        Account account = accountRepo.findById(request.getAccountId())
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        double amount = request.getAmount();
        if (amount <= 0 || amount > 20000) {
            return new Respuesta("Monto inválido para depósito", false);
        }
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setType("DEPOSIT");
        tx.setAmount(amount);
        tx.setDate(LocalDateTime.now());
        tx.setDescription(request.getConcept());
        transactionRepo.save(tx);

        return new Respuesta("Depósito exitoso", true);
    }

    public Respuesta withdraw(WithdrawRequest request) {
        Account account = accountRepo.findById(request.getAccountId())
            .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        double amount = request.getAmount();
        if (amount <= 0 || amount > 9000) {
            return new Respuesta("Monto inválido para retiro", false);
        }
        if (account.getBalance() < amount) {
            return new Respuesta("Saldo insuficiente", false);
        }
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        Transaction tx = new Transaction();
        tx.setAccount(account);
        tx.setType("WITHDRAW");
        tx.setAmount(amount);
        tx.setDate(LocalDateTime.now());
        tx.setDescription(request.getConcept());
        transactionRepo.save(tx);

        return new Respuesta("Retiro exitoso", true);
    }

    public Respuesta transfer(TransferRequest request) {
        Account from = accountRepo.findById(request.getSenderId())
            .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));
        Account to = accountRepo.findById(request.getRecipientId())
            .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));
        double amount = request.getAmount();
        if (amount <= 0 || amount > 20000) {
            return new Respuesta("Monto inválido para transferencia", false);
        }
        if (from.getBalance() < amount) {
            return new Respuesta("Saldo insuficiente", false);
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        accountRepo.save(from);
        accountRepo.save(to);

        Transaction out = new Transaction();
        out.setAccount(from);
        out.setType("TRANSFER_OUT");
        out.setAmount(amount);
        out.setDate(LocalDateTime.now());
        out.setDescription(request.getConcept());
        transactionRepo.save(out);

        Transaction in = new Transaction();
        in.setAccount(to);
        in.setType("TRANSFER_IN");
        in.setAmount(amount);
        in.setDate(LocalDateTime.now());
        in.setDescription(request.getConcept());
        transactionRepo.save(in);

        return new Respuesta("Transferencia completada", true);
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepo.findByAccountIdOrderByDateDesc(accountId);
    }
}