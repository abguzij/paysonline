package com.example.paysonline.exception;

public class PaymentHistoryEntityDoesntExistException extends Exception {
    public PaymentHistoryEntityDoesntExistException(String message) {
        super(message);
    }
}
