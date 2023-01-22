package com.crud.orders.exception;

import org.apache.commons.text.StringSubstitutor;

import javax.ws.rs.core.Response;
import java.util.Map;

public class ProductNotRegisteredException extends OrdersCrudException{
    public static final int STATUS_CODE = Response.Status.BAD_REQUEST.getStatusCode();

    public static final String MESSAGE = "Invalid product";
    private static final String DETAILS_TEMPLATE =
            "Product with code '${productCode}' is not registered in the database.";

    public ProductNotRegisteredException(String productCode) {
        super(
                new StringSubstitutor(Map.of("productCode", productCode)).replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE
        );
    }

    public ProductNotRegisteredException(String productCode, Throwable throwable_cause) {
        super(
                new StringSubstitutor(Map.of("productCode", productCode)).replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE,
                throwable_cause
        );
    }
}
