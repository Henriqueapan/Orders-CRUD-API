package com.crud.orders.exception;

import org.apache.commons.text.StringSubstitutor;

import javax.ws.rs.core.Response;
import java.util.Map;

public class CustomerAlreadyRegisteredException extends OrdersCrudException{

    public static final int STATUS_CODE = Response.Status.BAD_REQUEST.getStatusCode();

    public static final String MESSAGE = "Customer registration failed";

    private static final String DETAILS_TEMPLATE =
            "Customer with name '${customerName}' and address '${customerAddress}' is already registered.";

    public CustomerAlreadyRegisteredException(String customerName, String customerAddress) {
        super(
                new StringSubstitutor(Map.of("customerName", customerName, "customerAddress", customerAddress))
                        .replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE
        );
    }

    public CustomerAlreadyRegisteredException(String customerName, String customerAddress, Throwable throwable_cause) {
        super(
                new StringSubstitutor(Map.of("customerName", customerName, "customerAddress", customerAddress))
                        .replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE,
                throwable_cause
        );
    }
}
