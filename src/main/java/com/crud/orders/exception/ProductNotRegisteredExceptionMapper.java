package com.crud.orders.exception;

import com.crud.orders.dto.ErrorResponseDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class ProductNotRegisteredExceptionMapper implements ExceptionMapper<ProductNotRegisteredException> {
    private static final String MESSAGE = "Invalid product";

    @Override
    public Response toResponse(ProductNotRegisteredException productNotRegisteredException) {
        final String DETAILS = productNotRegisteredException.getMessage();

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                MESSAGE,
                DETAILS
        );

        return Response.status(productNotRegisteredException.STATUS_CODE).entity(
                Map.of("error", errorResponseDTO)
        ).build();
    }
}
