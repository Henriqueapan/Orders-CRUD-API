package com.crud.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.Valid;
import java.util.Currency;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder({"id", "title", "director", "authors", "actors"})
public class OrderDTO {
    private String id;

    private @Valid CustomerDTO customer;

    private Set<@Valid ProductDTO> order_products;

//    private Set<String> address;
//
//    private Date delivery_date;
//
//    private Currency delivery_pricing;
//
//    private String delivery_status;
//
//    private String payment_method;
}
