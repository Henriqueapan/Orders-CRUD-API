package com.crud.orders.exceptionMapper;

import com.crud.orders.dto.ErrorResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Provider
public class DTOConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {
    private final ObjectMapper mapper = new ObjectMapper();

    public static final String MESSAGE = "Invalid request body";
    public static final String DETAILS = "Some of the request body's arguments violate value restrictions";


    @Override
    public Response toResponse(ConstraintViolationException constraintViolationException) {

        Set<String> contraintViolations = constraintViolationException.getConstraintViolations().parallelStream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toSet());

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                MESSAGE,
                DETAILS,
                mapper.convertValue(contraintViolations, String[].class)
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("error", errorResponseDTO))
                .build();
    }
}
