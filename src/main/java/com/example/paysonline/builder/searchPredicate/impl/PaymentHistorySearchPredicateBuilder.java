package com.example.paysonline.builder.searchPredicate.impl;

import com.example.paysonline.builder.searchPredicate.AbstractSearchPredicateBuilder;
import com.example.paysonline.entity.PaymentHistoryEntity;
import com.example.paysonline.entity.QPaymentHistoryEntity;
import com.querydsl.core.types.Predicate;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaymentHistorySearchPredicateBuilder extends AbstractSearchPredicateBuilder
{
    private LocalDateTime startDateFilter;
    private LocalDateTime endDateFilter;
    private Long providerIdFilter;
    private Long serviceIdFilter;
    private String clientsCredentialsFilter;

    public PaymentHistorySearchPredicateBuilder() {
        super();
    }

    public PaymentHistorySearchPredicateBuilder(
            LocalDateTime startDateFilter,
            LocalDateTime endDateFilter,
            Long providerIdFilter,
            Long serviceIdFilter,
            String clientsCredentialsFilter
    ) {
        super();
        this.startDateFilter = startDateFilter;
        this.endDateFilter = endDateFilter;
        this.providerIdFilter = providerIdFilter;
        this.serviceIdFilter = serviceIdFilter;
        this.clientsCredentialsFilter = clientsCredentialsFilter;
    }

    @Override
    public Predicate buildPredicate() {
        QPaymentHistoryEntity root = QPaymentHistoryEntity.paymentHistoryEntity;
        if(Objects.nonNull(this.startDateFilter)){
            this.addFilterPredicate(root.createdAt.goe(this.startDateFilter));
        }
        if(Objects.nonNull(this.endDateFilter)){
            this.addFilterPredicate(root.createdAt.loe(this.endDateFilter));
        }
        if(Objects.nonNull(this.serviceIdFilter)){
            this.addFilterPredicate(root.serviceEntity.id.eq(this.serviceIdFilter));
        }
        if(Objects.nonNull(this.providerIdFilter)){
            this.addFilterPredicate(
                    root.serviceEntity.serviceGroupEntity.providerDetailsEntities.any()
                            .serviceProviderEntity.id.eq(this.providerIdFilter)
            );
        }
        if(Objects.nonNull(this.clientsCredentialsFilter)){
            this.addFilterPredicate(root.clientCredentials.eq(this.clientsCredentialsFilter));
        }
        return this.booleanBuilder;
    }
}
