package com.crud.orders.dto;

import io.smallrye.common.constraint.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    @NotNull
    private String name;

    @NotNull
    private String code;

    private long quantity = 1;
}
