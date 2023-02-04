package com.crud.orders.dto;

import com.crud.orders.enumeration.DeliveryStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.smallrye.common.constraint.NotNull;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
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

    private Long customerId;

    @NotNull
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

    @AssertTrue(message = "Exactly one of the two following parameters should be passed at a time: customerId or customer (CustomerDTO).")
    private boolean isValidCustomerDefinition() {
        return !(
                (customer == null && customerId == null)
                || (customer != null && customerId != null)
                );
    }
}
