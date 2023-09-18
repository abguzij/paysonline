package com.example.paysonline.repository;

import com.example.paysonline.entity.ProviderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderDetailsEntityRepository
        extends JpaRepository<ProviderDetailsEntity, Long>,
        QuerydslPredicateExecutor<ProviderDetailsEntity>
{
}
