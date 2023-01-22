package com.crud.orders.entity;

import com.crud.orders.pk.OrdersProductsPK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders_products")
public class OrdersProductsEntity {
    @EmbeddedId
    @JsonIgnore
    OrdersProductsPK id = new OrdersProductsPK();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonBackReference
    OrdersEntity order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    ProductsEntity product;

    private long product_quantity;
}
