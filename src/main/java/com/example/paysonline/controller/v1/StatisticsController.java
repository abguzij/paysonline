package com.example.paysonline.controller.v1;

import com.example.paysonline.builder.searchPredicate.AbstractSearchPredicateBuilder;
import com.example.paysonline.builder.searchPredicate.impl.PaymentHistorySearchPredicateBuilder;
import com.example.paysonline.dto.*;
import com.example.paysonline.entity.TransactionsHistoryEntity;
import com.example.paysonline.exception.PaymentHistoryNotFoundException;
import com.example.paysonline.exception.TransactionsNotFoundException;
import com.example.paysonline.service.StatisticsService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/payments-history")
    public List<PaymentHistoryResponseDto> getAllPaymentsSortedByDate(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) LocalDateTime startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Long providerId,
            @RequestParam(required = false) Long serviceId,
            @RequestParam(required = false) String clientCredentials,
            @RequestParam(required = false) Boolean isDescendingSort
    )
            throws PaymentHistoryNotFoundException
    {
        return this.statisticsService.getAllPaymentHistory(
                startDate, endDate, providerId, serviceId, clientCredentials, isDescendingSort
        );
    }

    @GetMapping("/payments-number")
    public PaymentsNumberResponseDto getPaymentsNumber(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) LocalDateTime startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Long providerId,
            @RequestParam(required = false) Long serviceId,
            @RequestParam(required = false) String clientCredentials
    )
            throws PaymentHistoryNotFoundException
    {
        return this.statisticsService.getNumberOfPayments(
                startDate, endDate, providerId, serviceId, clientCredentials
        );
    }

    @GetMapping("/payments-amount")
    public PaymentsAmountResponseDto getPaymentsAmount(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) LocalDateTime startDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(required = false) LocalDateTime endDate,
            @RequestParam(required = false) Long providerId,
            @RequestParam(required = false) Long serviceId,
            @RequestParam(required = false) String clientCredentials
    )
            throws PaymentHistoryNotFoundException
    {
        return this.statisticsService.getPaymentsAmount(
                startDate, endDate, providerId, serviceId, clientCredentials
        );
    }

    @GetMapping("/transactions-history")
    public List<TransactionsHistoryResponseDto> getTransactionsHistoryByDate(
            @QuerydslPredicate(root = TransactionsHistoryEntity.class) Predicate predicate
    )
            throws TransactionsNotFoundException
    {
        return this.statisticsService.getAllTransactionsHistory(predicate);
    }

    @GetMapping("/transactions-number")
    public TransactionsNumberResponseDto getTransactionsNumber(
            @QuerydslPredicate(root = TransactionsHistoryEntity.class) Predicate predicate
    )
            throws TransactionsNotFoundException
    {
        return this.statisticsService.getNumberOfTransactions(predicate);
    }

    @GetMapping("/transactions-amount")
    public TransactionsAmountResponseDto getTransactionsAmount(
            @QuerydslPredicate(root = TransactionsHistoryEntity.class) Predicate predicate
    )
            throws TransactionsNotFoundException
    {
        return this.statisticsService.getTransactionAmount(predicate);
    }
}
