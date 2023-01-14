package com.crud.orders.service;

import com.crud.orders.dto.OrderDTO;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class OrdersService {
    public OrderDTO getOrder(String id) {
        OrderDTO retOrderDTO = new OrderDTO();
        retOrderDTO.setId(id);

        return retOrderDTO;
    }
}
