package com.crud.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDTO {
    public ErrorResponseDTO(String message, String details, String[] constraintViolations, MismatchedInputErrorDTO
            mismatchedInput) {
        this.message = message;
        this.details = details;
        this.constraintViolations = constraintViolations;
        this.mismatchedInput = mismatchedInput;
    }

    public ErrorResponseDTO(String message, String details, MismatchedInputErrorDTO
            mismatchedInput) {
        this.message = message;
        this.details = details;
        this.mismatchedInput = mismatchedInput;
    }

    public ErrorResponseDTO(String message, String details, String[] constraintViolations) {
        this.message = message;
        this.details = details;
        this.constraintViolations = constraintViolations;
    }

    public ErrorResponseDTO(String message, String details) {
        this.message = message;
        this.details = details;
    }

    // Jackson Constructor
    public ErrorResponseDTO(){}

    private String message;

    private String details;

    private String[] constraintViolations;

    private MismatchedInputErrorDTO mismatchedInput;

    public void setConstraintViolations(String[] constraintViolations) { this.constraintViolations = constraintViolations; }

    public String[] getConstraintViolations() { return constraintViolations; }
}
