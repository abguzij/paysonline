package com.example.paysonline.repository;

import com.example.paysonline.entity.QServiceEntity;
import com.example.paysonline.entity.QServiceGroupEntity;
import com.example.paysonline.entity.ServiceGroupEntity;
import com.example.paysonline.entity.ServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceGroupEntityRepository
        extends JpaRepository<ServiceGroupEntity, Long>,
        QuerydslPredicateExecutor<ServiceGroupEntity>,
        QuerydslBinderCustomizer<QServiceGroupEntity>
{
    @Override
    default void customize(QuerydslBindings bindings, QServiceGroupEntity root) {
        bindings.bind(root.providerId).first(
                (stringPath, value) -> root.serviceProviderEntity.id.eq(value)
        );
    }
}
