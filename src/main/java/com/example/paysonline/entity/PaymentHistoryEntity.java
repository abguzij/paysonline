package com.example.paysonline.entity;

import com.example.paysonline.entity.atribute.PaymentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_history")
public class PaymentHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "client_full_name")
    private String clientFullName;
    @Column(name = "payment_amount")
    private Integer paymentAmount;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "client_credentials")
    private String clientCredentials;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceEntity serviceEntity;

    public PaymentHistoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public PaymentHistoryEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public PaymentHistoryEntity setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
        return this;
    }

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentHistoryEntity setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
        return this;
    }

    public PaymentStatus getConfirmed() {
        return paymentStatus;
    }

    public PaymentHistoryEntity setConfirmed(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public ServiceEntity getServiceEntity() {
        return serviceEntity;
    }

    public PaymentHistoryEntity setServiceEntity(ServiceEntity serviceEntity) {
        this.serviceEntity = serviceEntity;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public PaymentHistoryEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getClientCredentials() {
        return clientCredentials;
    }

    public PaymentHistoryEntity setClientCredentials(String clientCredentials) {
        this.clientCredentials = clientCredentials;
        return this;
    }
}
