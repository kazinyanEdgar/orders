package com.orders.dao;

import com.orders.model.ProductDescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDescriptionDAO {

    @Autowired
    private SessionFactory factory;

    public ProductDescription readByIdSerialNumber(String idSerialNumber) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            ProductDescription productDescription = session.get(ProductDescription.class, idSerialNumber);
            return productDescription;
        }
    }

    public void create(ProductDescription productDescription) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(productDescription);
            session.getTransaction().commit();
        }
    }

}
