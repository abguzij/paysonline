package com.example.paysonline.dto;

public class PaymentHistoryRequestDto {
    private Long serviceId;
    private String fullName;
    private String clientCredentials;
    private Integer paymentAmount;

    public PaymentHistoryRequestDto() {
    }

    public Long getServiceId() {
        return serviceId;
    }

    public PaymentHistoryRequestDto setServiceId(Long serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public PaymentHistoryRequestDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getClientCredentials() {
        return clientCredentials;
    }

    public PaymentHistoryRequestDto setClientCredentials(String clientCredentials) {
        this.clientCredentials = clientCredentials;
        return this;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentHistoryRequestDto setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }
}
