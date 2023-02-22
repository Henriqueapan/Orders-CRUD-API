package com.crud.orders.exceptionMapper;

import com.crud.orders.dto.ErrorResponseDTO;
import com.crud.orders.dto.MismatchedInputErrorDTO;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.jboss.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class MismatchedInputExceptionMapper implements ExceptionMapper<MismatchedInputException> {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final Logger LOG = Logger.getLogger(this.getClass());

    public static final String MESSAGE = "Invalid request body";
    public static final String DETAILS = "Some request body`s field contains an argument of invalid type";

    @Override
    public Response toResponse(MismatchedInputException mismatchedInputException) {

        final String fieldName =
                mismatchedInputException.getPath().stream().map(JsonMappingException.Reference::getFieldName)
                        .collect(Collectors.joining("."));

        final String originalMessageSplit =
                mismatchedInputException.getOriginalMessage().split("from ")[1];

        final String invalidArgType =
                originalMessageSplit.substring(0, originalMessageSplit.indexOf(" "));

        String invalidArgValue = new String();

        if (invalidArgType.equals("String")) {
            invalidArgValue =
                    originalMessageSplit.substring(originalMessageSplit.indexOf(" "), originalMessageSplit.indexOf(":"))
                    .replaceAll("\\\"", "").trim();
        } else if (invalidArgType.equals("Boolean")) {
            invalidArgValue =
                    originalMessageSplit.substring(originalMessageSplit.indexOf("VALUE_"), originalMessageSplit.indexOf("`)"))
                    .toLowerCase().trim().replace("value_", "");
        } else {
            invalidArgValue = "--Node--";
        }

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                MESSAGE,
                DETAILS,
                new MismatchedInputErrorDTO(
                        fieldName,
                        mismatchedInputException.getTargetType().getSimpleName(),
                        invalidArgType,
                        invalidArgValue
                )
        );

        return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", errorResponseDTO)).build();
    }
}
