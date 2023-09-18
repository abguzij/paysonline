package com.example.paysonline.controller.v1;

import com.example.paysonline.dto.PaymentHistoryRequestDto;
import com.example.paysonline.dto.PaymentHistoryResponseDto;
import com.example.paysonline.dto.TransactionsHistoryResponseDto;
import com.example.paysonline.exception.*;
import com.example.paysonline.response.PaymentDeclinedResponse;
import com.example.paysonline.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @Autowired
    public PaymentController (
            PaymentService paymentService
    ) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay-by-service")
    public PaymentHistoryResponseDto payByServiceId(
            @RequestBody PaymentHistoryRequestDto paymentHistoryRequestDto
    )
            throws IncorrectAmountException
    {
        return this.paymentService.createNewPayment(paymentHistoryRequestDto);
    }

    @PutMapping("/confirm-payment/{paymentId}")
    public TransactionsHistoryResponseDto confirmPayment(
            @PathVariable Long paymentId
    )
            throws PaymentHistoryEntityDoesntExistException,
            InvalidPaymentHistoryEntityException,
            ProviderDetailsNotFoundException
    {
        return this.paymentService.confirmPayment(paymentId);
    }

    @PutMapping("/decline-payment/{paymentId}")
    public PaymentDeclinedResponse declinePayment(
            @PathVariable Long paymentId
    )
            throws PaymentHistoryEntityDoesntExistException,
            InvalidPaymentHistoryEntityException
    {
        return this.paymentService.declinePayment(paymentId);
    }
}
