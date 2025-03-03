package com.parspec.order.processing.unit.orderprocessingunit.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
