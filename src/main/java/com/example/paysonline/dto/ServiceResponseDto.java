package com.example.paysonline.dto;

public class ServiceResponseDto {
    private Long id;
    private String title;
    private Integer minPaymentAmount;
    private Integer commission;
    private ServiceGroupResponseDto serviceGroupResponseDto;

    public ServiceResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public ServiceResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceResponseDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getMinPaymentAmount() {
        return minPaymentAmount;
    }

    public ServiceResponseDto setMinPaymentAmount(Integer minPaymentAmount) {
        this.minPaymentAmount = minPaymentAmount;
        return this;
    }

    public Integer getCommission() {
        return commission;
    }

    public ServiceResponseDto setCommission(Integer commission) {
        this.commission = commission;
        return this;
    }

    public ServiceGroupResponseDto getServiceGroupDto() {
        return serviceGroupResponseDto;
    }

    public ServiceResponseDto setServiceGroupDto(ServiceGroupResponseDto serviceGroupResponseDto) {
        this.serviceGroupResponseDto = serviceGroupResponseDto;
        return this;
    }
}
