package com.crud.orders.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersCrudException extends RuntimeException{

    private int statusCode;

    private String details;

    private String message;

    public OrdersCrudException(String details, String message, int statusCode){
        super(details);
        this.statusCode = statusCode;
        this.details = details;
        this.message = message;
    }

    public OrdersCrudException(String details, String message, int statusCode, Throwable throwableCause){
        super(details, throwableCause);
        this.statusCode = statusCode;
        this.details = details;
        this.message = message;
    }
}
