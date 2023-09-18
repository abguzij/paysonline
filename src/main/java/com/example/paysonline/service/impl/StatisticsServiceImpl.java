package com.example.paysonline.service.impl;

import com.example.paysonline.builder.searchPredicate.AbstractSearchPredicateBuilder;
import com.example.paysonline.builder.searchPredicate.impl.PaymentHistorySearchPredicateBuilder;
import com.example.paysonline.dto.*;
import com.example.paysonline.entity.PaymentHistoryEntity;
import com.example.paysonline.entity.TransactionsHistoryEntity;
import com.example.paysonline.exception.PaymentHistoryNotFoundException;
import com.example.paysonline.exception.TransactionsNotFoundException;
import com.example.paysonline.mapper.PaymentMapper;
import com.example.paysonline.repository.PaymentHistoryEntityRepository;
import com.example.paysonline.repository.TransactionsHistoryEntityRepository;
import com.example.paysonline.service.StatisticsService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final TransactionsHistoryEntityRepository transactionsHistoryEntityRepository;
    private final PaymentHistoryEntityRepository paymentHistoryEntityRepository;

    @Autowired
    public StatisticsServiceImpl(
            TransactionsHistoryEntityRepository transactionsHistoryEntityRepository,
            PaymentHistoryEntityRepository paymentHistoryEntityRepository
    ) {
        this.transactionsHistoryEntityRepository = transactionsHistoryEntityRepository;
        this.paymentHistoryEntityRepository = paymentHistoryEntityRepository;
    }

    @Override
    public List<TransactionsHistoryResponseDto> getAllTransactionsHistory(Predicate predicate)
            throws TransactionsNotFoundException
    {
        Iterable<TransactionsHistoryEntity> transactionsHistoryEntitiesIterable =
                this.transactionsHistoryEntityRepository.findAll(predicate);
        List<TransactionsHistoryResponseDto> transactionsHistoryResponseDtoList =
                StreamSupport
                        .stream(transactionsHistoryEntitiesIterable.spliterator(), false)
                        .map(PaymentMapper::mapTransactionsHistoryEntityToDto)
                        .collect(Collectors.toList());

        if (transactionsHistoryResponseDtoList.isEmpty()) {
            throw new TransactionsNotFoundException("По введенным параметрам не найдено ни одной транзакции!");
        }
        return transactionsHistoryResponseDtoList;
    }

    @Override
    public List<TransactionsHistoryEntity> getAllTransactionsHistoryEntities(Predicate predicate)
            throws TransactionsNotFoundException
    {
        Iterable<TransactionsHistoryEntity> transactionsHistoryEntitiesIterable =
                this.transactionsHistoryEntityRepository.findAll(predicate);
        List<TransactionsHistoryEntity> transactionsHistoryEntities =
                StreamSupport
                        .stream(transactionsHistoryEntitiesIterable.spliterator(), false)
                        .collect(Collectors.toList());

        if (transactionsHistoryEntities.isEmpty()) {
            throw new TransactionsNotFoundException("По введенным параметрам транзакций не найдено!");
        }
        return transactionsHistoryEntities;
    }

    @Override
    public List<PaymentHistoryResponseDto> getAllPaymentHistory(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials,
            Boolean isDescending
    )
            throws PaymentHistoryNotFoundException
    {
        return this.getAllPaymentHistoryEntities(
                        startDate, endDate, providerId, serviceId, clientCredentials, isDescending
                )
                .stream()
                .map(PaymentMapper::mapPaymentHistoryEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentHistoryEntity> getAllPaymentHistoryEntities(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials,
            Boolean isDescending
    )
            throws PaymentHistoryNotFoundException
    {
        AbstractSearchPredicateBuilder searchPredicateBuilder =
                new PaymentHistorySearchPredicateBuilder(startDate, endDate, providerId, serviceId, clientCredentials);
        Predicate predicate = searchPredicateBuilder.buildPredicate();

        Iterable<PaymentHistoryEntity> paymentHistoryEntitiesIterable;
        if(Objects.isNull(isDescending)){
            paymentHistoryEntitiesIterable
                    = this.paymentHistoryEntityRepository.findAll(predicate);
            return convertIterableOfPaymentHistoryEntitiesToList(paymentHistoryEntitiesIterable);
        }
        if(isDescending){
            paymentHistoryEntitiesIterable = this.paymentHistoryEntityRepository.findAll(
                    predicate,
                    Sort.by(Sort.Direction.DESC, "createdAt")
            );
            return convertIterableOfPaymentHistoryEntitiesToList(paymentHistoryEntitiesIterable);
        }
        paymentHistoryEntitiesIterable = this.paymentHistoryEntityRepository.findAll(
                predicate,
                Sort.by(Sort.Direction.ASC, "createdAt")
        );

        List<PaymentHistoryEntity> paymentHistoryEntities =
                convertIterableOfPaymentHistoryEntitiesToList(paymentHistoryEntitiesIterable);
        if(paymentHistoryEntities.isEmpty()) {
            throw new PaymentHistoryNotFoundException(
                    "Записей в истории платежей по указанным парметрам не найдено"
            );
        }
        return paymentHistoryEntities;
    }

    @Override
    public TransactionsNumberResponseDto getNumberOfTransactions(Predicate predicate)
            throws TransactionsNotFoundException
    {
        return new TransactionsNumberResponseDto().setTransactionsNumber(
                this.getAllTransactionsHistoryEntities(predicate).size()
        );
    }

    @Override
    public TransactionsAmountResponseDto getTransactionAmount(Predicate predicate)
            throws TransactionsNotFoundException
    {
        List<TransactionsHistoryEntity> transactionsHistoryEntities =
                this.getAllTransactionsHistoryEntities(predicate);
        Integer transactionAmount = 0;
        for (TransactionsHistoryEntity transaction:
             transactionsHistoryEntities) {
            transactionAmount += transaction.getPaymentAmount();
        }
        return new TransactionsAmountResponseDto().setTransactionsAmount(transactionAmount);
    }

    @Override
    public PaymentsNumberResponseDto getNumberOfPayments(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials
    ) throws PaymentHistoryNotFoundException {
        List<PaymentHistoryEntity> paymentHistoryEntities =
                this.getAllPaymentHistoryEntities(
                        startDate, endDate, providerId, serviceId, clientCredentials, null
                );
        return new PaymentsNumberResponseDto()
                .setPaymentsNumber(paymentHistoryEntities.size())
                .setStartDateFilter(startDate)
                .setEndDateFilter(endDate)
                .setProviderIdFilter(providerId)
                .setServiceIdFilter(serviceId)
                .setClientCredentialsFilter(clientCredentials);
    }

    @Override
    public PaymentsAmountResponseDto getPaymentsAmount(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials
    )
            throws PaymentHistoryNotFoundException
    {
        List<PaymentHistoryEntity> paymentHistoryEntities =
                this.getAllPaymentHistoryEntities(
                        startDate, endDate, providerId, serviceId, clientCredentials, null
                );

        Integer paymentsAmount = 0;
        for (PaymentHistoryEntity payment:
             paymentHistoryEntities) {
            paymentsAmount += payment.getPaymentAmount();
        }
        return new PaymentsAmountResponseDto()
                .setPaymentsAmount(paymentsAmount)
                .setStartDateFilter(startDate)
                .setEndDateFilter(endDate)
                .setProviderIdFilter(providerId)
                .setServiceIdFilter(serviceId)
                .setClientCredentialsFilter(clientCredentials);
    }

    private List<PaymentHistoryEntity> convertIterableOfPaymentHistoryEntitiesToList(
            Iterable<PaymentHistoryEntity> paymentHistoryEntitiesIterable
    ) {
        return StreamSupport
                .stream(paymentHistoryEntitiesIterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
