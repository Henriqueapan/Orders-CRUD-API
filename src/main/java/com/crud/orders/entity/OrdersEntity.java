package com.crud.orders.entity;

import com.crud.orders.service.OrdersService;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.inject.Inject;
import javax.persistence.*;
import java.util.Currency;
import java.util.Date;
import java.util.Map;
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
    Set<OrdersProductsEntity> products;
}
