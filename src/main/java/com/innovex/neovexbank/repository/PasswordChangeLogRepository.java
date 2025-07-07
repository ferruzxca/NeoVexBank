package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.PasswordChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordChangeLogRepository extends JpaRepository<PasswordChangeLog, Long> {
}