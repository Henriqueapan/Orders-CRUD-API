package com.crud.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
public class ProductsEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
}
