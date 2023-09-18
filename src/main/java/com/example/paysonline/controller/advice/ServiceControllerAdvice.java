package com.example.paysonline.controller.advice;

import com.example.paysonline.controller.v1.ServiceController;
import com.example.paysonline.exception.ServiceProviderGroupNotFoundException;
import com.example.paysonline.exception.ServiceProviderNotFoundException;
import com.example.paysonline.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.ServiceNotFoundException;

@RestControllerAdvice(basePackageClasses = ServiceController.class)
public class ServiceControllerAdvice {

    @ExceptionHandler(value = ServiceProviderNotFoundException.class)
    public ErrorResponse handleServiceProviderNotFoundException(ServiceProviderNotFoundException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.OK).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = ServiceProviderGroupNotFoundException.class)
    public ErrorResponse handleServiceProviderGroupNotFoundException(ServiceProviderGroupNotFoundException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.OK).setMessage(e.getMessage());
    }

    @ExceptionHandler(value = ServiceNotFoundException.class)
    public ErrorResponse handleServiceNotFoundException(ServiceNotFoundException e) {
        return new ErrorResponse().setHttpStatus(HttpStatus.OK).setMessage(e.getMessage());
    }
}
