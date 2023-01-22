package com.crud.orders.entity;

import com.crud.orders.dto.OrderDTO;
import com.crud.orders.enumeration.DeliveryStatusEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

@Entity
@Table(
        name = "Orders",
        uniqueConstraints =
            @UniqueConstraint(columnNames = {"order_code"})
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "OrdersEntity.findOrderById", query = "SELECT o FROM OrdersEntity o WHERE o.id=:id")
public class OrdersEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_code")
    private String order_code;

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    Set<OrdersProductsEntity> products;

    @Column(name = "delivery_status")
    @JsonProperty("delivery_status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatusEnum deliveryStatus;
}
