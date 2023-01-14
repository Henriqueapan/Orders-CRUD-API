package com.crud.orders.service;

import com.crud.orders.dto.OrderDTO;
import com.crud.orders.entity.CustomerEntity;
import com.crud.orders.entity.OrdersEntity;
import com.crud.orders.entity.ProductsEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@ApplicationScoped
public class OrdersService {
    @Inject
    EntityManager em;
    public OrderDTO getOrder(String id) {
        OrderDTO retOrderDTO = new OrderDTO();
        retOrderDTO.setId(id);

        return retOrderDTO;
   }

//   @Transactional
//   public boolean registerOrder(OrderDTO orderDTO) {
//       OrdersEntity orderEntity = new OrdersEntity();
//       CustomerEntity customerEntity = new CustomerEntity();
//       ProductsEntity productEntity = new ProductsEntity();
//
//       customerEntity.setName(orderDTO.getCustomer());
//
//       productEntity.setName();
//
//       orderEntity.setCustomer(customerEntity);
//
//   }
}
