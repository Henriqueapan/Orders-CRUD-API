package com.crud.orders.service;

import com.crud.orders.dto.CustomerDTO;
import com.crud.orders.dto.OrderDTO;
import com.crud.orders.dto.ProductDTO;
import com.crud.orders.entity.CustomerEntity;
import com.crud.orders.entity.OrdersEntity;
import com.crud.orders.entity.OrdersProductsEntity;
import com.crud.orders.entity.ProductsEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrdersService {

    @Inject
    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction usrTrx;

    final ObjectMapper mapper = new ObjectMapper();
    public OrderDTO getOrder(String id) {
        OrderDTO retOrderDTO = new OrderDTO();
        retOrderDTO.setId(id);

        return retOrderDTO;
    }

    @Transactional
    public boolean registerOrder(OrderDTO orderDTO) {
        CustomerDTO customerDTO = orderDTO.getCustomer();
        OrdersEntity orderEntity = this._registerOrdersCustomer(customerDTO);

        Set<ProductDTO> productDTOSet = orderDTO.getOrder_products();
        Set<OrdersProductsEntity> ordersProductsEntitySet =
                productDTOSet.stream().map(
                        productDTO -> {
                            OrdersProductsEntity ordersProductsEntity = new OrdersProductsEntity();

                            ProductsEntity productsEntity =
                                    (ProductsEntity) em.createNamedQuery("ProductsEntity.findByCode")
                                    .setParameter("code", productDTO.getCode())
                                    .getSingleResult();

                            ordersProductsEntity.setProduct_quantity(productDTO.getQuantity());
                            ordersProductsEntity.setProduct(productsEntity);
                            ordersProductsEntity.setOrder(orderEntity);

                            return ordersProductsEntity;
                        }
                )
                .collect(Collectors.toSet());

        ordersProductsEntitySet.forEach(ordersProductsEntity -> em.persist(ordersProductsEntity));

        orderEntity.setProducts(ordersProductsEntitySet);

        em.persist(orderEntity);

        return true;
   }

   private OrdersEntity _registerOrdersCustomer(CustomerDTO customerDTO) {
       OrdersEntity orderEntity = new OrdersEntity();

       CustomerEntity customerEntity = (CustomerEntity)
               em.createNamedQuery("CustomerEntity.findByNameAddressPair")
                       .setParameter("name", customerDTO.getName())
                       .setParameter("address", customerDTO.getAddress())
                       .getSingleResult();

       orderEntity.setCustomer(customerEntity);

       return orderEntity;
    }

    @Transactional
    private void registerOrderProductPair(ProductsEntity productsEntity, OrdersEntity ordersEntity) {
       OrdersProductsEntity orderProductEntity = new OrdersProductsEntity();

//       orderProductEntity.
   }
}
