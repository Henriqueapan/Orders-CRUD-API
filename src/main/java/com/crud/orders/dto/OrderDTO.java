package com.crud.orders.dto;

import com.crud.orders.enumeration.DeliveryStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.Valid;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private DeliveryStatusEnum delivery_status;
//
//    private String payment_method;
}
