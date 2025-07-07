package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}