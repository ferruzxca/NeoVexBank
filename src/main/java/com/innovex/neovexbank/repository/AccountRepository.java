package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Busca todas las cuentas cuyo customer.user.id == userId
    List<Account> findByCustomerUserId(Long userId);
}