package com.crud.orders.exception;

import org.apache.commons.text.StringSubstitutor;

import javax.ws.rs.core.Response;
import java.util.Map;

public class CustomerNotFoundException extends OrdersCrudException{

    public static final int STATUS_CODE = Response.Status.NOT_FOUND.getStatusCode();

    public static final String MESSAGE = "Customer not found.";

    private static final String DETAILS_TEMPLATE =
            "Customer with name '${customerName}' and address '${customerAddress}' was not found.";

    public CustomerNotFoundException(String customerName, String customerAddress) {
        super(
                new StringSubstitutor(Map.of("customerName", customerName, "customerAddress", customerAddress))
                        .replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE
        );
    }

    public CustomerNotFoundException(String customerName, String customerAddress, Throwable throwableCause) {
        super(
                new StringSubstitutor(Map.of("customerName", customerName, "customerAddress", customerAddress))
                        .replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE,
                throwableCause
        );
    }
}
