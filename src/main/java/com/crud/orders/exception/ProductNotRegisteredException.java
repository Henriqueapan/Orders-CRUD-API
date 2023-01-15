package com.crud.orders.exception;

public class ProductNotRegisteredException extends RuntimeException{
    public final int STATUS_CODE = 400;

    public ProductNotRegisteredException(String msg) {
        super(msg);
    }

    public ProductNotRegisteredException(String msg, Throwable throwable_cause) {
        super(msg, throwable_cause);
    }

}
