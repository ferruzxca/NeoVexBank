package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findBySenderAccountId(Long senderAccountId);
    List<Transfer> findByReceiverAccountId(Long receiverAccountId);
}