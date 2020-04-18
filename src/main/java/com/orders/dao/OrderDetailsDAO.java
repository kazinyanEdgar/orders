package com.orders.dao;

import com.orders.model.Order;
import com.orders.model.OrderDetails;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailsDAO {

    @Autowired
    private SessionFactory factory;

    public List<OrderDetails> readByIdOrder(Order idOrder) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query<OrderDetails> query = session.createQuery("from OrderDetails where idOrderDetails = :idOrderDetails", OrderDetails.class);
            query.setParameter("idOrderDetails", idOrder);
            List<OrderDetails> orderDetails = query.list();

            if (orderDetails.size() != 0) {
                for (int i = 0; i < orderDetails.size(); i++) {
                    Hibernate.initialize(orderDetails.get(i).getSerialNumber());
                    Hibernate.initialize(orderDetails.get(i).getIdOrderDetails());
                }
            }
            return orderDetails;
        }
    }

    public void create(OrderDetails orderDetails) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(orderDetails);
            session.getTransaction().commit();
        }
    }

}