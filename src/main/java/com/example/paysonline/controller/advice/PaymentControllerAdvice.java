package com.example.paysonline.controller.advice;

import com.example.paysonline.controller.v1.PaymentController;
import com.example.paysonline.exception.*;
import com.example.paysonline.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = PaymentController.class)
public class PaymentControllerAdvice {
    @ExceptionHandler(value = IncorrectAmountException.class)
    public ErrorResponse handleIncorrectAmountException(IncorrectAmountException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = ServiceProviderNotFoundException.class)
    public ErrorResponse handleServiceProviderEntityDoesntExistException(ServiceProviderNotFoundException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = ServiceEntityDoesntExistException.class)
    public ErrorResponse handleServiceEntityDoesntExistException(ServiceEntityDoesntExistException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = PaymentHistoryEntityDoesntExistException.class)
    public ErrorResponse handlePaymentHistoryEntityDoesntExistException(PaymentHistoryEntityDoesntExistException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = InvalidPaymentHistoryEntityException.class)
    public ErrorResponse handleInvalidPaymentHistoryEntityException(InvalidPaymentHistoryEntityException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }

    @ExceptionHandler(value = ProviderDetailsNotFoundException.class)
    public ErrorResponse handleProviderDetailsNotFoundException(ProviderDetailsNotFoundException e){
        return new ErrorResponse().setHttpStatus(HttpStatus.BAD_REQUEST).setMessage(e.getLocalizedMessage());
    }
}
