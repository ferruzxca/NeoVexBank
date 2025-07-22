package com.innovex.neovexbank.service;

import com.innovex.neovexbank.model.Account;
import com.innovex.neovexbank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    // Si usas UserId (por referencia desde Customer):
    // public List<Account> getAccountsByUserId(Long userId) {
    //     return accountRepository.findByCustomerUserId(userId);
    // }
}