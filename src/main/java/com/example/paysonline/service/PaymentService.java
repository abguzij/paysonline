package com.example.paysonline.service;

import com.example.paysonline.dto.PaymentHistoryRequestDto;
import com.example.paysonline.dto.PaymentHistoryResponseDto;
import com.example.paysonline.dto.TransactionsHistoryResponseDto;
import com.example.paysonline.entity.PaymentHistoryEntity;
import com.example.paysonline.entity.ProviderDetailsEntity;
import com.example.paysonline.entity.TransactionsHistoryEntity;
import com.example.paysonline.exception.IncorrectAmountException;
import com.example.paysonline.exception.InvalidPaymentHistoryEntityException;
import com.example.paysonline.exception.PaymentHistoryEntityDoesntExistException;
import com.example.paysonline.exception.ProviderDetailsNotFoundException;
import com.example.paysonline.response.PaymentDeclinedResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PaymentService {
    @Transactional
    PaymentHistoryResponseDto createNewPayment(PaymentHistoryRequestDto paymentHistoryRequestDto)
            throws IncorrectAmountException;

    @Transactional
    TransactionsHistoryResponseDto confirmPayment(Long paymentId)
            throws PaymentHistoryEntityDoesntExistException,
            InvalidPaymentHistoryEntityException, ProviderDetailsNotFoundException;

    @Transactional
    PaymentDeclinedResponse declinePayment(Long paymentId) throws PaymentHistoryEntityDoesntExistException, InvalidPaymentHistoryEntityException;

    ProviderDetailsEntity getProviderDetailsByServiceId(Long serviceId) throws ProviderDetailsNotFoundException;

    List<PaymentHistoryEntity> getPaymentHistoryByServiceId(Long serviceId);

    List<TransactionsHistoryEntity> getTransactionsHistoryByProviderId(Long providerId);
}
