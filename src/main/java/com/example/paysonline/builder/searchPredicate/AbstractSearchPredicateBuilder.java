package com.example.paysonline.builder.searchPredicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public abstract class AbstractSearchPredicateBuilder {
    protected BooleanBuilder booleanBuilder;

    public AbstractSearchPredicateBuilder() {
        this.booleanBuilder = new BooleanBuilder();
    }

    protected void addFilterPredicate(Predicate predicate){
        this.booleanBuilder.and(predicate);
    }

    public abstract Predicate buildPredicate();
}
