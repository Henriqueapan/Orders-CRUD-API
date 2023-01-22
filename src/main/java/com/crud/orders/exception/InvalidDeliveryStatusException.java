package com.crud.orders.exception;

import org.apache.commons.text.StringSubstitutor;

import javax.ws.rs.core.Response;
import java.util.Map;

public class InvalidDeliveryStatusException extends OrdersCrudException{

    public static final int STATUS_CODE = Response.Status.BAD_REQUEST.getStatusCode();

    public static final String MESSAGE = "Invalid Delivery Status";

    private static final String DETAILS_TEMPLATE =
            "Delivery Status '${deliveryStatus}' is not valid.";

    public InvalidDeliveryStatusException(String status){
        super(
                new StringSubstitutor(Map.of("deliveryStatus", status)).replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE
        );
    }

    public InvalidDeliveryStatusException(String status, Throwable throwableCause){
        super(
                new StringSubstitutor(Map.of("deliveryStatus", status)).replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE,
                throwableCause
                );
    }
}
