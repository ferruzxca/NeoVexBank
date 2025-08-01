package com.innovex.neovexbank.dto;

public class TransferRequest {
    private Long senderAccountId;
    private Long receiverAccountId;
    private Double amount;
    private String description;


    // Getters y setters
    public Long getSenderAccountId() { return senderAccountId; }
    public void setSenderAccountId(Long senderAccountId) { this.senderAccountId = senderAccountId; }

    public Long getReceiverAccountId() { return receiverAccountId; }
    public void setReceiverAccountId(Long receiverAccountId) { this.receiverAccountId = receiverAccountId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }


    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}