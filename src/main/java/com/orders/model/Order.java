package com.orders.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {

    private int idOrder;

    private String customerName;

    private String customerAddress;

    private int totalAmount;

    private LocalDate dateManufacture;

    @Override
    public String toString() {
        return "idOrder=" + idOrder + ", customerName=" + customerName + ", customerAddress=" + customerAddress + ", totalAmount="
                + totalAmount + ", dateManufacture=" + dateManufacture;
    }
}
