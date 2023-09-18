package com.example.paysonline.repository;

import com.example.paysonline.entity.QTransactionsHistoryEntity;
import com.example.paysonline.entity.TransactionsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionsHistoryEntityRepository
        extends JpaRepository<TransactionsHistoryEntity, Long>,
        QuerydslPredicateExecutor<TransactionsHistoryEntity>,
        QuerydslBinderCustomizer<QTransactionsHistoryEntity>
{
    @Override
    default void customize(QuerydslBindings bindings, QTransactionsHistoryEntity root) {
        bindings.bind(root.providerId).first(
                (stringPath, value) -> root.providerDetailsEntity.serviceProviderEntity.id.eq(value)
        );
        bindings.bind(root.startDate).first(
                (stringPath, value) -> root.createdAt.goe(value)
        );
        bindings.bind(root.endDate).first(
                (stringPath, value) -> root.createdAt.loe(value)
        );
    }
}
