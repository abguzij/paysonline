package com.example.paysonline.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provider_details")
public class ProviderDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "bank_account")
    private String bankAccount;
    @ManyToOne
    @JoinColumn(name = "service_provider_id")
    private ServiceProviderEntity serviceProviderEntity;
    @ManyToOne
    @JoinColumn(name = "service_group_id")
    private ServiceGroupEntity serviceGroupEntity;

    @OneToMany(mappedBy = "providerDetailsEntity")
    private List<TransactionsHistoryEntity> transactionsHistoryEntities;

    public ProviderDetailsEntity() {
    }

    public Long getId() {
        return id;
    }

    public ProviderDetailsEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public ProviderDetailsEntity setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public ServiceProviderEntity getServiceProviderEntity() {
        return serviceProviderEntity;
    }

    public ProviderDetailsEntity setServiceProviderEntity(ServiceProviderEntity serviceProviderEntity) {
        this.serviceProviderEntity = serviceProviderEntity;
        return this;
    }

    public ServiceGroupEntity getServiceGroupEntity() {
        return serviceGroupEntity;
    }

    public ProviderDetailsEntity setServiceGroupEntity(ServiceGroupEntity serviceGroupEntity) {
        this.serviceGroupEntity = serviceGroupEntity;
        return this;
    }

    public List<TransactionsHistoryEntity> getTransactionsHistoryEntities() {
        return transactionsHistoryEntities;
    }

    public ProviderDetailsEntity setTransactionsHistoryEntities(List<TransactionsHistoryEntity> transactionsHistoryEntities) {
        this.transactionsHistoryEntities = transactionsHistoryEntities;
        return this;
    }
}
