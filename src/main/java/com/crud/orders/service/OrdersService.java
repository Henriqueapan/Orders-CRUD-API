package com.crud.orders.service;

import com.crud.orders.dto.CustomerDTO;
import com.crud.orders.dto.OrderDTO;
import com.crud.orders.dto.ProductDTO;
import com.crud.orders.entity.CustomerEntity;
import com.crud.orders.entity.OrdersEntity;
import com.crud.orders.entity.OrdersProductsEntity;
import com.crud.orders.entity.ProductsEntity;
import com.crud.orders.exception.OrderNotFoundException;
import com.crud.orders.exception.OrdersCrudException;
import com.crud.orders.exception.ProductNotRegisteredException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrdersService {

    @Inject
    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction usrTrx;

    @Inject
    CustomersService customersService;

    final ObjectMapper mapper = new ObjectMapper();
    public OrdersEntity getOrder(Long id) {
//        OrderDTO retOrderDTO = new OrderDTO();
//        retOrderDTO.setId(id);
//
//        return retOrderDTO;
        Query q = em.createNamedQuery("OrdersEntity.findOrderById").setParameter("id", id);
        OrdersEntity ordersEntity = new OrdersEntity();
        try {
            ordersEntity = (OrdersEntity) q.getSingleResult();
        } catch (NoResultException noResExc) {
            throw new OrderNotFoundException(id, noResExc.getCause());
        }
        return ordersEntity;
    }

    @Transactional
    public boolean registerOrder(OrderDTO orderDTO) {
        CustomerDTO customerDTO = orderDTO.getCustomer();
        OrdersEntity orderEntity = new OrdersEntity();

        // Checks if Customer is registered
        if(_checkCustomersExistance(customerDTO)) orderEntity = this._registerOrdersCustomer(customerDTO);
        else {
            CustomerEntity customerEntity = this._registerNewCustomer(customerDTO);
            orderEntity.setCustomer(customerEntity);
        }

        Set<ProductDTO> productDTOSet = orderDTO.getOrder_products();

        final OrdersEntity finalOrderEntity = orderEntity;

        Set<OrdersProductsEntity> ordersProductsEntitySet =
                productDTOSet.stream().map(productDTO ->  _setOrdersProductsEntity(productDTO, finalOrderEntity))
                .collect(Collectors.toSet());

        // This could be implemented in the first stream
        ordersProductsEntitySet.forEach(ordersProductsEntity -> em.persist(ordersProductsEntity));

        orderEntity.setProducts(ordersProductsEntitySet);

        // First persist saves the entity withou it`s order_code
        em.persist(orderEntity);

        try {
            orderEntity.setOrder_code(_generateOrderCode(orderEntity));
            em.persist(orderEntity);
        } catch (RuntimeException runtimeException) {
            em.remove(orderEntity);
            throw new OrdersCrudException(
                    "Error while generating order`s code",
                    "Could not generate order`s code for the requested order. Please contact the support.",
                    500,
                    runtimeException.getCause()
            );
        }

        return true;
   }

   public List<OrdersEntity> getAllOrders() {
        Query q = em.createQuery("SELECT o FROM OrdersEntity o");
        List<OrdersEntity> ordersEntities;
        try {
            ordersEntities = q.getResultList();
        } catch (NoResultException noResExc) {
            return List.of();
        }
        return ordersEntities;
   }

   private String _generateOrderCode(OrdersEntity orderEntity) {
       final String strId = orderEntity.getId().toString();
       final String customerId = orderEntity.getCustomer().getId().toString();

       String codeId = strId + customerId;
       if(codeId.length() < 7) {
           for(int i = codeId.length(); i < 7; i++) {
               codeId = codeId + '0';
           }
       }

       return "ORD" + codeId;
   }

   private OrdersProductsEntity _setOrdersProductsEntity(ProductDTO productDTO, OrdersEntity finalOrderEntity) throws ProductNotRegisteredException {
       OrdersProductsEntity ordersProductsEntity = new OrdersProductsEntity();
       ProductsEntity productsEntity = new ProductsEntity();

       try {
           productsEntity =
                   (ProductsEntity) em.createNamedQuery("ProductsEntity.findByCode")
                           .setParameter("code", productDTO.getCode())
                           .getSingleResult();
       } catch (NoResultException noResExc) {
           throw new ProductNotRegisteredException(productDTO.getCode());
       }

       ordersProductsEntity.setProduct_quantity(productDTO.getQuantity());
       ordersProductsEntity.setProduct(productsEntity);
       ordersProductsEntity.setOrder(finalOrderEntity);

       return ordersProductsEntity;
   }

    private OrdersEntity _registerOrdersCustomer(CustomerDTO customerDTO) {
       OrdersEntity orderEntity = new OrdersEntity();

       CustomerEntity customerEntity = (CustomerEntity)
               _getCustomersNamedQuery(customerDTO)
                       .getSingleResult();

       orderEntity.setCustomer(customerEntity);

       return orderEntity;
    }

    private CustomerEntity _registerNewCustomer(CustomerDTO customerDTO) {
        Long newCustomersId = this.customersService.registerCustomer(customerDTO);
        if (newCustomersId < 1) {
            return null;
        }
        return em.find(CustomerEntity.class, newCustomersId);
    }

    private boolean _checkCustomersExistance(CustomerDTO customerDTO) {
        long qCount = _getCustomersNamedQuery(customerDTO)
                .getResultStream()
                .count();

        return qCount == 1;
    }

    private Query _getCustomersNamedQuery(CustomerDTO customerDTO) {
        return em.createNamedQuery("CustomerEntity.findByNameAddressPair")
                .setParameter("name", customerDTO.getName())
                .setParameter("address", customerDTO.getAddress());
    }
}
