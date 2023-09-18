package com.example.paysonline.dto;

public class TransactionsAmountResponseDto {
    private Integer transactionsAmount;

    public TransactionsAmountResponseDto() {
    }

    public Integer getTransactionsAmount() {
        return transactionsAmount;
    }

    public TransactionsAmountResponseDto setTransactionsAmount(Integer transactionsAmount) {
        this.transactionsAmount = transactionsAmount;
        return this;
    }
}
