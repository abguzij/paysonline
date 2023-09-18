package com.example.paysonline.entity;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions_history")
public class TransactionsHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "payment_amount")
    private Integer paymentAmount;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity serviceEntity;
    @ManyToOne
    @JoinColumn(name = "provider_details_id")
    private ProviderDetailsEntity providerDetailsEntity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @QueryType(value = PropertyType.DATE)
    @Transient
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @QueryType(value = PropertyType.DATE)
    @Transient
    private LocalDateTime endDate;
    @QueryType(value = PropertyType.COMPARABLE)
    @Transient
    private Long providerId;

    public TransactionsHistoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public TransactionsHistoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public TransactionsHistoryEntity setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public ServiceEntity getServiceEntity() {
        return serviceEntity;
    }

    public TransactionsHistoryEntity setServiceEntity(ServiceEntity serviceEntity) {
        this.serviceEntity = serviceEntity;
        return this;
    }

    public ProviderDetailsEntity getProviderDetailsEntity() {
        return providerDetailsEntity;
    }

    public TransactionsHistoryEntity setProviderDetailsEntity(ProviderDetailsEntity providerDetailsEntity) {
        this.providerDetailsEntity = providerDetailsEntity;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public TransactionsHistoryEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public TransactionsHistoryEntity setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public TransactionsHistoryEntity setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public Long getProviderId() {
        return providerId;
    }

    public TransactionsHistoryEntity setProviderId(Long providerId) {
        this.providerId = providerId;
        return this;
    }
}
