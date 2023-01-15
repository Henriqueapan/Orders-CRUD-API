package com.crud.orders.entity;

import com.crud.orders.dto.CustomerDTO;
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
@Table(
        name = "Customers",
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"name", "address"})
)
@NamedQuery(name = "CustomerEntity.findByNameAddressPair", query = "SELECT u from CustomerEntity u WHERE " +
        "u.name=:name AND u.address=:address")
public class CustomerEntity {

    public CustomerEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String address;
}
