package com.example.paysonline.entity;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "commission")
    private Integer commission;
    @Column(name = "min_payment_amount")
    private Integer minPaymentAmount;
    @ManyToOne
    @JoinColumn(name = "service_group_id")
    private ServiceGroupEntity serviceGroupEntity;

    @OneToMany(mappedBy = "serviceEntity")
    private List<TransactionsHistoryEntity> transactionsHistoryEntities;
    @OneToMany(mappedBy = "serviceEntity")
    private List<PaymentHistoryEntity> paymentHistoryEntities;

    @Transient
    @QueryType(value = PropertyType.COMPARABLE)
    private Long providerId;
    @Transient
    @QueryType(value = PropertyType.COMPARABLE)
    private Long serviceGroupId;

    public ServiceEntity() {
    }

    public Long getId() {
        return id;
    }

    public ServiceEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ServiceEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getCommission() {
        return commission;
    }

    public ServiceEntity setCommission(Integer commission) {
        this.commission = commission;
        return this;
    }

    public Integer getMinPaymentAmount() {
        return minPaymentAmount;
    }

    public ServiceEntity setMinPaymentAmount(Integer minPaymentAmount) {
        this.minPaymentAmount = minPaymentAmount;
        return this;
    }

    public ServiceGroupEntity getServiceGroupEntity() {
        return serviceGroupEntity;
    }

    public ServiceEntity setServiceGroupEntity(ServiceGroupEntity serviceGroupEntity) {
        this.serviceGroupEntity = serviceGroupEntity;
        return this;
    }

    public List<TransactionsHistoryEntity> getTransactionsHistoryEntities() {
        return transactionsHistoryEntities;
    }

    public ServiceEntity setTransactionsHistoryEntities(List<TransactionsHistoryEntity> transactionsHistoryEntities) {
        this.transactionsHistoryEntities = transactionsHistoryEntities;
        return this;
    }

    public List<PaymentHistoryEntity> getPaymentHistoryEntities() {
        return paymentHistoryEntities;
    }

    public ServiceEntity setPaymentHistoryEntities(List<PaymentHistoryEntity> paymentHistoryEntities) {
        this.paymentHistoryEntities = paymentHistoryEntities;
        return this;
    }

    public Long getProviderId() {
        return providerId;
    }

    public ServiceEntity setProviderId(Long providerId) {
        this.providerId = providerId;
        return this;
    }

    public Long getServiceGroupId() {
        return serviceGroupId;
    }

    public ServiceEntity setServiceGroupId(Long serviceGroupId) {
        this.serviceGroupId = serviceGroupId;
        return this;
    }
}
