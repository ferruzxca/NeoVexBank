package com.innovex.neovexbank.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LoginSession> loginSessions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PasswordChangeLog> passwordChangeLogs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AuditLog> auditLogs;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LoginSession> getLoginSessions() {
        return loginSessions;
    }

    public void setLoginSessions(List<LoginSession> loginSessions) {
        this.loginSessions = loginSessions;
    }

    public List<PasswordChangeLog> getPasswordChangeLogs() {
        return passwordChangeLogs;
    }

    public void setPasswordChangeLogs(List<PasswordChangeLog> passwordChangeLogs) {
        this.passwordChangeLogs = passwordChangeLogs;
    }

    public List<AuditLog> getAuditLogs() {
        return auditLogs;
    }

    public void setAuditLogs(List<AuditLog> auditLogs) {
        this.auditLogs = auditLogs;
    }
}