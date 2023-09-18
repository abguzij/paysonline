package com.example.paysonline.dto;

import java.time.LocalDateTime;

public class PaymentsAmountResponseDto {
    private Integer paymentsAmount;
    private LocalDateTime startDateFilter;
    private LocalDateTime endDateFilter;
    private Long providerIdFilter;
    private Long serviceIdFilter;
    private String clientCredentialsFilter;

    public PaymentsAmountResponseDto() {
    }

    public Integer getPaymentsAmount() {
        return paymentsAmount;
    }

    public PaymentsAmountResponseDto setPaymentsAmount(Integer paymentsAmount) {
        this.paymentsAmount = paymentsAmount;
        return this;
    }

    public LocalDateTime getStartDateFilter() {
        return startDateFilter;
    }

    public PaymentsAmountResponseDto setStartDateFilter(LocalDateTime startDateFilter) {
        this.startDateFilter = startDateFilter;
        return this;
    }

    public LocalDateTime getEndDateFilter() {
        return endDateFilter;
    }

    public PaymentsAmountResponseDto setEndDateFilter(LocalDateTime endDateFilter) {
        this.endDateFilter = endDateFilter;
        return this;
    }

    public Long getProviderIdFilter() {
        return providerIdFilter;
    }

    public PaymentsAmountResponseDto setProviderIdFilter(Long providerIdFilter) {
        this.providerIdFilter = providerIdFilter;
        return this;
    }

    public Long getServiceIdFilter() {
        return serviceIdFilter;
    }

    public PaymentsAmountResponseDto setServiceIdFilter(Long serviceIdFilter) {
        this.serviceIdFilter = serviceIdFilter;
        return this;
    }

    public String getClientCredentialsFilter() {
        return clientCredentialsFilter;
    }

    public PaymentsAmountResponseDto setClientCredentialsFilter(String clientCredentialsFilter) {
        this.clientCredentialsFilter = clientCredentialsFilter;
        return this;
    }
}
