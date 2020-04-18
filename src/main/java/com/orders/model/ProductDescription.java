package com.orders.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDescription {

    private String idSerialNumber;

    private String productName;

    private String description;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate dateSerialProduction;

    @Override
    public String toString() {
        return "idSerialNumber=" + idSerialNumber + ", productName=" + productName + ", description=" + description + ", dateSerialProduction=" + dateSerialProduction;
    }
}
