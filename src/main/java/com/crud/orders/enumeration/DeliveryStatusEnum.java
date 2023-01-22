package com.crud.orders.enumeration;


import java.util.Arrays;

public enum DeliveryStatusEnum {
    PROCESSING, AWAITING_PICKUP, SHIPPED, IN_TRANSIT, DELIVERED, MISSING_PACKAGE;

    public static DeliveryStatusEnum findByStatusString(String status) {
        return Arrays.stream(values()).filter(value -> value.toString().equals(status)).findFirst().orElse(null);
    }
}