package com.example.paysonline.repository;

import com.example.paysonline.entity.QServiceProviderEntity;
import com.example.paysonline.entity.ServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderEntityRepository
        extends JpaRepository<ServiceProviderEntity, Long>,
        QuerydslPredicateExecutor<ServiceProviderEntity>,
        QuerydslBinderCustomizer<QServiceProviderEntity>
{
    @Override
    default void customize(QuerydslBindings bindings, QServiceProviderEntity root){}
}
