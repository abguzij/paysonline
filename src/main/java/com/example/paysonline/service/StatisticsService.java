package com.example.paysonline.service;

import com.example.paysonline.dto.*;
import com.example.paysonline.entity.PaymentHistoryEntity;
import com.example.paysonline.entity.TransactionsHistoryEntity;
import com.example.paysonline.exception.PaymentHistoryNotFoundException;
import com.example.paysonline.exception.TransactionsNotFoundException;
import com.querydsl.core.types.Predicate;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsService {
    List<TransactionsHistoryResponseDto> getAllTransactionsHistory(Predicate predicate) throws TransactionsNotFoundException;
    List<TransactionsHistoryEntity> getAllTransactionsHistoryEntities(Predicate predicate) throws TransactionsNotFoundException;

    List<PaymentHistoryResponseDto> getAllPaymentHistory(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials,
            Boolean isDescending
    ) throws PaymentHistoryNotFoundException;

    List<PaymentHistoryEntity> getAllPaymentHistoryEntities(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials,
            Boolean isDescending
    ) throws PaymentHistoryNotFoundException;

    TransactionsNumberResponseDto getNumberOfTransactions(Predicate predicate) throws TransactionsNotFoundException;

    TransactionsAmountResponseDto getTransactionAmount(Predicate predicate) throws TransactionsNotFoundException;

    PaymentsNumberResponseDto getNumberOfPayments(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials
    ) throws PaymentHistoryNotFoundException;

    PaymentsAmountResponseDto getPaymentsAmount(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Long providerId,
            Long serviceId,
            String clientCredentials
    ) throws PaymentHistoryNotFoundException;
}
