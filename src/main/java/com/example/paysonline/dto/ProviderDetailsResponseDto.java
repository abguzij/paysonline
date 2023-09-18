package com.example.paysonline.dto;

public class ProviderDetailsResponseDto {
    private Long id;
    private String bankAccount;
    private ServiceProviderResponseDto serviceProviderResponseDto;
    private ServiceGroupResponseDto serviceGroupResponseDto;

    public ProviderDetailsResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public ProviderDetailsResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public ProviderDetailsResponseDto setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public ServiceProviderResponseDto getServiceProviderDto() {
        return serviceProviderResponseDto;
    }

    public ProviderDetailsResponseDto setServiceProviderDto(ServiceProviderResponseDto serviceProviderResponseDto) {
        this.serviceProviderResponseDto = serviceProviderResponseDto;
        return this;
    }

    public ServiceGroupResponseDto getServiceGroupDto() {
        return serviceGroupResponseDto;
    }

    public ProviderDetailsResponseDto setServiceGroupDto(ServiceGroupResponseDto serviceGroupResponseDto) {
        this.serviceGroupResponseDto = serviceGroupResponseDto;
        return this;
    }
}
