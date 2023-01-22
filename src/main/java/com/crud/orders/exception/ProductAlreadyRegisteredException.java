package com.crud.orders.exception;

import org.apache.commons.text.StringSubstitutor;

import javax.ws.rs.core.Response;
import java.util.Map;

public class ProductAlreadyRegisteredException extends OrdersCrudException{

    public static final int STATUS_CODE = Response.Status.BAD_REQUEST.getStatusCode();

    public static final String MESSAGE = "Product registration failed";

    private static final String DETAILS_TEMPLATE =
            "Product with code '${productCode}' is already registered.";

    public ProductAlreadyRegisteredException(String productCode) {
        super(
                new StringSubstitutor(Map.of("productCode", productCode))
                        .replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE
        );
    }

    public ProductAlreadyRegisteredException(String productCode, Throwable throwableCause) {
        super(
                new StringSubstitutor(Map.of("productCode", productCode))
                        .replace(DETAILS_TEMPLATE),
                MESSAGE,
                STATUS_CODE,
                throwableCause
        );
    }
}
