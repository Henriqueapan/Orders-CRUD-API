package com.crud.orders.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.inject.Named;
import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
@NamedQuery(
        name = "ProductsEntity.findByCode",
        query = "SELECT p FROM ProductsEntity p WHERE p.code =:code"
)
public class ProductsEntity {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;
}
