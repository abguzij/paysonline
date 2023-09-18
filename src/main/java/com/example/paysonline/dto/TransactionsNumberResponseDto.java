package com.example.paysonline.dto;

import java.time.LocalDateTime;

public class TransactionsNumberResponseDto {
    private Integer transactionsNumber;

    public TransactionsNumberResponseDto() {
    }

    public Integer getTransactionsNumber() {
        return transactionsNumber;
    }

    public TransactionsNumberResponseDto setTransactionsNumber(Integer transactionsNumber) {
        this.transactionsNumber = transactionsNumber;
        return this;
    }
}
