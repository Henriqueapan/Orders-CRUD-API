package com.crud.orders.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MismatchedInputErrorDTO {

    public MismatchedInputErrorDTO(String fieldName, String expectedType, String actualType, String actualValue) {
        this.fieldName = fieldName;
        this.expectedType = expectedType;
        this.actualType = actualType;
        this.actualValue = actualValue;
    }

    // Jackson constructor
    public MismatchedInputErrorDTO() {}

    private String fieldName;

    private String expectedType;

    private String actualType;

    private String actualValue;
}
