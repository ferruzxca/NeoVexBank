package com.innovex.neovexbank.service;

import com.innovex.neovexbank.dto.DepositRequest;
import com.innovex.neovexbank.dto.TransferRequest;
import com.innovex.neovexbank.dto.WithdrawRequest;
import com.innovex.neovexbank.model.Account;
import com.innovex.neovexbank.model.Transaction;
import com.innovex.neovexbank.repository.AccountRepository;
import com.innovex.neovexbank.repository.TransactionRepository;
import com.innovex.neovexbank.utils.Respuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public Respuesta deposit(DepositRequest request) {
        Optional<Account> accountOpt = accountRepository.findById(request.getAccountId());
        if (!accountOpt.isPresent()) {
            return new Respuesta("Cuenta no encontrada", false);
        }
        Account account = accountOpt.get();
        account.setBalance(account.getBalance() + request.getAmount());
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType("DEPÓSITO");
        transaction.setAmount(request.getAmount());
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription(request.getDescription());
        transactionRepository.save(transaction);

        return new Respuesta("Depósito realizado con éxito", true);
    }

    public Respuesta withdraw(WithdrawRequest request) {
        Optional<Account> accountOpt = accountRepository.findById(request.getAccountId());
        if (!accountOpt.isPresent()) {
            return new Respuesta("Cuenta no encontrada", false);
        }
        Account account = accountOpt.get();
        if (account.getBalance() < request.getAmount()) {
            return new Respuesta("Saldo insuficiente", false);
        }
        account.setBalance(account.getBalance() - request.getAmount());
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setType("RETIRO");
        transaction.setAmount(-request.getAmount());
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription(request.getDescription());
        transactionRepository.save(transaction);

        return new Respuesta("Retiro realizado con éxito", true);
    }

    public Respuesta transfer(TransferRequest request) {
        Optional<Account> senderOpt = accountRepository.findById(request.getSenderAccountId());
        Optional<Account> receiverOpt = accountRepository.findById(request.getReceiverAccountId());
        if (!senderOpt.isPresent() || !receiverOpt.isPresent()) {
            return new Respuesta("Cuenta origen o destino no encontrada", false);
        }
        Account sender = senderOpt.get();
        Account receiver = receiverOpt.get();
        if (sender.getBalance() < request.getAmount()) {
            return new Respuesta("Saldo insuficiente", false);
        }
        sender.setBalance(sender.getBalance() - request.getAmount());
        receiver.setBalance(receiver.getBalance() + request.getAmount());
        accountRepository.save(sender);
        accountRepository.save(receiver);

        // Registrar movimientos en transaction
        Transaction t1 = new Transaction();
        t1.setAccount(sender);
        t1.setType("TRANSFERENCIA ENVIADA");
        t1.setAmount(-request.getAmount());
        t1.setDate(LocalDateTime.now());
        t1.setDescription("Transferencia a cuenta " + receiver.getAccountNumber());
        transactionRepository.save(t1);

        Transaction t2 = new Transaction();
        t2.setAccount(receiver);
        t2.setType("TRANSFERENCIA RECIBIDA");
        t2.setAmount(request.getAmount());
        t2.setDate(LocalDateTime.now());
        t2.setDescription("Transferencia de cuenta " + sender.getAccountNumber());
        transactionRepository.save(t2);

        return new Respuesta("Transferencia realizada con éxito", true);
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
