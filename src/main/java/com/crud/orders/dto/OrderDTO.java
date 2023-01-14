package com.crud.orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

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

    private String customer;

    private Map<String, Integer> order_products;

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
