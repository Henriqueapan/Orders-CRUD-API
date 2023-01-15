package com.crud.orders.pk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class OrdersProductsPK implements Serializable {
    protected Long productId;

    protected Long orderId;

    public boolean equals(Object that) {
        if(!(that instanceof OrdersProductsPK)) return false;
        if (this == that) return true;
        OrdersProductsPK castThat = (OrdersProductsPK) that;
        return productId.equals(castThat.productId) && orderId.equals(castThat.orderId);
    }

    public int hashCode() {
        final int prime = 29;
        int hash = 7;
        hash = hash * prime + this.orderId.hashCode();
        hash = hash * prime + this.productId.hashCode();
        return hash;
    }
}
