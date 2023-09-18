package com.example.paysonline.dto;

public class ServiceGroupResponseDto {
    private Long id;
    private String title;
    private ServiceProviderResponseDto serviceProviderResponseDto;

    public ServiceGroupResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public ServiceGroupResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceGroupResponseDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public ServiceProviderResponseDto getServiceProviderDto() {
        return serviceProviderResponseDto;
    }

    public ServiceGroupResponseDto setServiceProviderDto(ServiceProviderResponseDto serviceProviderResponseDto) {
        this.serviceProviderResponseDto = serviceProviderResponseDto;
        return this;
    }
}
