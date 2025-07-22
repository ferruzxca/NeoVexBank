package com.innovex.neovexbank.dto;

public class DepositRequest {
    private Long accountId;
    private Double amount;
    private String description; // <-- Añadido

    // Getter y Setter para accountId
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    // Getter y Setter para amount
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    // Getter y Setter para description
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}