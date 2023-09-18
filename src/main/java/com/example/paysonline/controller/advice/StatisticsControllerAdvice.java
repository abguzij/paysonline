package com.example.paysonline.controller.advice;

import com.example.paysonline.controller.v1.StatisticsController;
import com.example.paysonline.exception.PaymentHistoryNotFoundException;
import com.example.paysonline.exception.TransactionsNotFoundException;
import com.example.paysonline.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = StatisticsController.class)
public class StatisticsControllerAdvice {
    @ExceptionHandler(value = TransactionsNotFoundException.class)
    public ErrorResponse handleTransactionsNotFoundException(TransactionsNotFoundException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.OK).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = PaymentHistoryNotFoundException.class)
    public ErrorResponse handlePaymentHistoryNotFoundException(PaymentHistoryNotFoundException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.OK).setMessage(e.getMessage());
    }
}
