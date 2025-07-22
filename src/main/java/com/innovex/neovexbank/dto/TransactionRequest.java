package com.innovex.neovexbank.dto;

public class TransactionRequest {
    private Long accountId;
    private String type;         // "DEPÓSITO" o "RETIRO"
    private Double amount;
    private String description;

    // Getters y setters
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}