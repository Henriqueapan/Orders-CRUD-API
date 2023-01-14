package com.crud.orders.entity;

import com.crud.orders.pk.OrdersProductsPK;
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
    OrdersProductsPK id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    OrdersEntity order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    ProductsEntity product;

    private int product_quantity;
}
