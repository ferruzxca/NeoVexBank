package com.innovex.neovexbank.dto;

public class DepositRequest {
    private Long accountId;
    private Double amount;
    private String concept;

    public DepositRequest() {
    }

    public DepositRequest(Long accountId, Double amount, String concept) {
        this.accountId = accountId;
        this.amount = amount;
        this.concept = concept;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}