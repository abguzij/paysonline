package com.example.paysonline.dto;

import java.time.LocalDateTime;

public class TransactionsHistoryResponseDto {
    private Long id;
    private Integer paymentAmount;
    private ServiceResponseDto serviceResponseDto;
    private LocalDateTime createdAt;
    private ProviderDetailsResponseDto providerDetailsResponseDto;

    public TransactionsHistoryResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public TransactionsHistoryResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public TransactionsHistoryResponseDto setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public ServiceResponseDto getServiceResponseDtoDto() {
        return serviceResponseDto;
    }

    public TransactionsHistoryResponseDto setServiceResponseDto(ServiceResponseDto serviceResponseDto) {
        this.serviceResponseDto = serviceResponseDto;
        return this;
    }

    public ProviderDetailsResponseDto getProviderDetailsResponseDto() {
        return providerDetailsResponseDto;
    }

    public TransactionsHistoryResponseDto setProviderDetailsResponseDto(ProviderDetailsResponseDto providerDetailsResponseDto) {
        this.providerDetailsResponseDto = providerDetailsResponseDto;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TransactionsHistoryResponseDto setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
