package com.innovex.neovexbank.service;

import com.innovex.neovexbank.model.Account;
import com.innovex.neovexbank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Suma el balance de todas las cuentas asociadas al usuario dado.
     *
     * @param userId ID del usuario
     * @return total del balance
     */
    public double getBalanceByUserId(Long userId) {
        // Asume que AccountRepository tiene un método List<Account> findByCustomerUserId(Long userId)
        List<Account> cuentas = accountRepository.findByCustomerUserId(userId);
        return cuentas.stream()
                      .mapToDouble(Account::getBalance)
                      .sum();
    }
}