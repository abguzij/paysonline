package com.example.paysonline.response;

import org.springframework.http.HttpStatus;

public class PaymentDeclinedResponse {
    private HttpStatus httpStatus;
    private String message;

    public PaymentDeclinedResponse() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public PaymentDeclinedResponse setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public PaymentDeclinedResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
