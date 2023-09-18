package com.example.paysonline.repository;

import com.example.paysonline.entity.PaymentHistoryEntity;
import com.example.paysonline.entity.ServiceEntity;
import com.example.paysonline.entity.TransactionsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentHistoryEntityRepository
        extends JpaRepository<PaymentHistoryEntity, Long>,
        QuerydslPredicateExecutor<PaymentHistoryEntity>
{
    PaymentHistoryEntity getPaymentHistoryEntityById(Long paymentHistoryId);

    List<PaymentHistoryEntity> getPaymentHistoryEntitiesByServiceEntityId(Long serviceId);
}
