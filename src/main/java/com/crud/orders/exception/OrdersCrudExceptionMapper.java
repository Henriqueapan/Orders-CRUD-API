package com.crud.orders.exception;

import com.crud.orders.dto.ErrorResponseDTO;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class OrdersCrudExceptionMapper implements ExceptionMapper<OrdersCrudException> {
    @Override
    public Response toResponse(OrdersCrudException ordersCrudException) {
        final String DETAILS = ordersCrudException.getDetails();

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                ordersCrudException.getMessage(),
                DETAILS
        );

        return Response.status(ordersCrudException.getStatusCode()).entity(
                Map.of("error", errorResponseDTO)
        ).build();
    }
}
