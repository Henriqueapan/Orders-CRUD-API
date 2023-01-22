package com.crud.orders.exceptionMapper;

import com.crud.orders.dto.ErrorResponseDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static final String MESSAGE = "Error when trying to convert the request body to JSON";

    @Override
    public Response toResponse(JsonParseException exception) {
        final String DETAILS = exception.getOriginalMessage();

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                MESSAGE,
                DETAILS
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", errorResponseDTO))
                .build();
    }
}
