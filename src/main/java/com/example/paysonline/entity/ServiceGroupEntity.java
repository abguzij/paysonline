package com.example.paysonline.entity;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service_group")
public class ServiceGroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private ServiceProviderEntity serviceProviderEntity;

    @OneToMany(mappedBy = "serviceGroupEntity")
    private List<ServiceEntity> serviceEntities;
    @OneToMany(mappedBy = "serviceGroupEntity")
    private List<ProviderDetailsEntity> providerDetailsEntities;

    @Transient
    @QueryType(value = PropertyType.COMPARABLE)
    private Long providerId;

    public ServiceGroupEntity() {
    }

    public Long getId() {
        return id;
    }

    public ServiceGroupEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceGroupEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public ServiceProviderEntity getServiceProviderEntity() {
        return serviceProviderEntity;
    }

    public ServiceGroupEntity setServiceProviderEntity(ServiceProviderEntity serviceProviderEntity) {
        this.serviceProviderEntity = serviceProviderEntity;
        return this;
    }

    public List<ServiceEntity> getServiceEntities() {
        return serviceEntities;
    }

    public ServiceGroupEntity setServiceEntities(List<ServiceEntity> serviceEntities) {
        this.serviceEntities = serviceEntities;
        return this;
    }

    public List<ProviderDetailsEntity> getProviderDetailsEntities() {
        return providerDetailsEntities;
    }

    public ServiceGroupEntity setProviderDetailsEntities(List<ProviderDetailsEntity> providerDetailsEntities) {
        this.providerDetailsEntities = providerDetailsEntities;
        return this;
    }

    public Long getProviderId() {
        return providerId;
    }

    public ServiceGroupEntity setProviderId(Long providerId) {
        this.providerId = providerId;
        return this;
    }
}
