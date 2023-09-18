package com.example.paysonline.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

public class PaymentsNumberResponseDto {
    private Integer paymentsNumber;
    private LocalDateTime startDateFilter;
    private LocalDateTime endDateFilter;
    private Long providerIdFilter;
    private Long serviceIdFilter;
    private String clientCredentialsFilter;

    public PaymentsNumberResponseDto() {
    }

    public Integer getPaymentsNumber() {
        return paymentsNumber;
    }

    public PaymentsNumberResponseDto setPaymentsNumber(Integer paymentsNumber) {
        this.paymentsNumber = paymentsNumber;
        return this;
    }

    public LocalDateTime getStartDateFilter() {
        return startDateFilter;
    }

    public PaymentsNumberResponseDto setStartDateFilter(LocalDateTime startDateFilter) {
        this.startDateFilter = startDateFilter;
        return this;
    }

    public LocalDateTime getEndDateFilter() {
        return endDateFilter;
    }

    public PaymentsNumberResponseDto setEndDateFilter(LocalDateTime endDateFilter) {
        this.endDateFilter = endDateFilter;
        return this;
    }

    public Long getProviderIdFilter() {
        return providerIdFilter;
    }

    public PaymentsNumberResponseDto setProviderIdFilter(Long providerIdFilter) {
        this.providerIdFilter = providerIdFilter;
        return this;
    }

    public Long getServiceIdFilter() {
        return serviceIdFilter;
    }

    public PaymentsNumberResponseDto setServiceIdFilter(Long serviceIdFilter) {
        this.serviceIdFilter = serviceIdFilter;
        return this;
    }

    public String getClientCredentialsFilter() {
        return clientCredentialsFilter;
    }

    public PaymentsNumberResponseDto setClientCredentialsFilter(String clientCredentialsFilter) {
        this.clientCredentialsFilter = clientCredentialsFilter;
        return this;
    }
}
