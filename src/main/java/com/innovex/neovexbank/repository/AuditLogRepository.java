package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}