package com.crud.orders.exception;

import org.apache.commons.text.StringSubstitutor;

import javax.ws.rs.core.Response;
import java.util.Map;

public class OrderNotFoundException extends OrdersCrudException{

    public static final int STATUS_CODE = Response.Status.NOT_FOUND.getStatusCode();

    public static final String MESSAGE = "Order not found.";

    private static final String DETAILS_TEMPLATE = "Order with id '${orderId}' was not found.";

    public OrderNotFoundException(Long id){
        super(
                new StringSubstitutor(Map.of("orderId", id)).replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE
        );
    }

    public OrderNotFoundException(Long id, Throwable throwableCause){
        super(
                new StringSubstitutor(Map.of("orderId", id)).replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE,
                throwableCause
        );
    }
}
