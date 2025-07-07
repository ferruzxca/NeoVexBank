package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}