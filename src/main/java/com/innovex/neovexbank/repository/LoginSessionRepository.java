package com.innovex.neovexbank.repository;

import com.innovex.neovexbank.model.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {
}