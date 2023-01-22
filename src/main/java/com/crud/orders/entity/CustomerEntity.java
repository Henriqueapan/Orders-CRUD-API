package com.crud.orders.entity;

import com.crud.orders.dto.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.ALWAYS)
@Table(
        name = "Customers",
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"name", "address"})
)
@NamedQuery(name = "CustomerEntity.findByNameAddressPair", query = "SELECT c FROM CustomerEntity c WHERE " +
        "c.name=:name AND c.address=:address")
@NamedQuery(name = "CustomerEntity.findById", query = "SELECT c FROM CustomerEntity c WHERE c.id=:id")
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

    private String country;

    private String state;

    private String city;

    @Column(columnDefinition = "int8 not null default 0")
    private long zipCode;
}
