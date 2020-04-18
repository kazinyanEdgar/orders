package com.orders.dao;

import com.orders.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO {

    @Autowired
    private SessionFactory factory;

    public List<Order> getAllTheOrders() {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            List<Order> orders = session.createQuery("from Order", Order.class).list();
            return orders;
        }
    }

    public Order read(Integer id) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            return order;
        }
    }
}
