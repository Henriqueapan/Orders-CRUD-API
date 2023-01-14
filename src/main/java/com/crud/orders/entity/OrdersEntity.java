package com.crud.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Currency;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "Orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

//    private String order_code;

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    Set<OrdersProductsEntity> products;
}
