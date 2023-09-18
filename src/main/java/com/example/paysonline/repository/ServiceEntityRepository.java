package com.example.paysonline.repository;

import com.example.paysonline.entity.QServiceEntity;
import com.example.paysonline.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceEntityRepository
        extends JpaRepository<ServiceEntity, Long>,
        QuerydslPredicateExecutor<ServiceEntity>,
        QuerydslBinderCustomizer<QServiceEntity>
{
    @Override
    default void customize(QuerydslBindings bindings, QServiceEntity root) {
        bindings.bind(root.providerId).first(
                (stringPath, value) -> root.serviceGroupEntity.serviceProviderEntity.id.eq(value)
        );
        bindings.bind(root.serviceGroupId).first(
                (stringPath, value) -> root.serviceGroupEntity.id.eq(value)
        );
    }

    public ServiceEntity getServiceEntityById(Long serviceEntityId);
}
