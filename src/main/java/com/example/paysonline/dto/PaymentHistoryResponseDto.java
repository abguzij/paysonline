package com.example.paysonline.dto;

import java.time.LocalDateTime;

public class PaymentHistoryResponseDto {
    private Long id;
    private String clientFullName;
    private String clientCredentials;
    private Integer paymentAmount;
    private LocalDateTime createdAt;
    private ServiceResponseDto serviceResponseDto;

    public PaymentHistoryResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public PaymentHistoryResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public PaymentHistoryResponseDto setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
        return this;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentHistoryResponseDto setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PaymentHistoryResponseDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getClientCredentials() {
        return clientCredentials;
    }

    public PaymentHistoryResponseDto setClientCredentials(String clientCredentials) {
        this.clientCredentials = clientCredentials;
        return this;
    }

    public ServiceResponseDto getServiceResponseDto() {
        return serviceResponseDto;
    }

    public PaymentHistoryResponseDto setServiceResponseDto(ServiceResponseDto serviceResponseDto) {
        this.serviceResponseDto = serviceResponseDto;
        return this;
    }
}
