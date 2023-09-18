package com.example.paysonline.entity;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_provider")
public class ServiceProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "serviceProviderEntity")
    private List<ServiceGroupEntity> serviceGroupEntities;
    @OneToMany(mappedBy = "serviceProviderEntity")
    private List<ProviderDetailsEntity> providerDetailsEntities;

    public ServiceProviderEntity() {
    }

    public Long getId() {
        return id;
    }

    public ServiceProviderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceProviderEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<ServiceGroupEntity> getServiceGroupEntities() {
        return serviceGroupEntities;
    }

    public ServiceProviderEntity setServiceGroupEntities(List<ServiceGroupEntity> serviceGroupEntities) {
        this.serviceGroupEntities = serviceGroupEntities;
        return this;
    }

    public List<ProviderDetailsEntity> getProviderDetailsEntities() {
        return providerDetailsEntities;
    }

    public ServiceProviderEntity setProviderDetailsEntities(List<ProviderDetailsEntity> providerDetailsEntities) {
        this.providerDetailsEntities = providerDetailsEntities;
        return this;
    }
}
