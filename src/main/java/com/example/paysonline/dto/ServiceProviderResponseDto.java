package com.example.paysonline.dto;

public class ServiceProviderResponseDto {
    private Long id;
    private String title;

    public ServiceProviderResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public ServiceProviderResponseDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceProviderResponseDto setTitle(String title) {
        this.title = title;
        return this;
    }
}
