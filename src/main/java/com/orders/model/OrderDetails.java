package com.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    private int id;

    private ProductDescription serialNumber;

    private int number;

    private Order idOrderDetails;

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", number=" + number +
                ", idOrderDetails=" + idOrderDetails +
                '}';
    }
}