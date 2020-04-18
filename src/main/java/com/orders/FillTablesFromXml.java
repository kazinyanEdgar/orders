package com.orders;

import com.orders.dao.OrderDAO;
import com.orders.dao.OrderDetailsDAO;
import com.orders.dao.ProductDescriptionDAO;
import com.orders.model.CollectionProducts;
import com.orders.model.Order;
import com.orders.model.OrderDetails;
import com.orders.model.ProductDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class FillTablesFromXml {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderDetailsDAO orderDetailsDAO;

    @Autowired
    private ProductDescriptionDAO productDescriptionDAO;

    public void parseXml() {
        if (productDescriptionDAO.readByIdSerialNumber("1q2e1z611") != null)
            return;

        File file = new File("dataForProducts.xml");
        CollectionProducts collection = null;
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionProducts.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            collection = (CollectionProducts) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.err.println("Unmarshalling failed " + e);
            e.printStackTrace();
        }

        for (ProductDescription product : collection.getProducts())
            productDescriptionDAO.create(product);

        fillOrdersDetails();
    }

    public void fillOrdersDetails() {
        OrderDetails orderDetails1 = new OrderDetails();
        ProductDescription productDescription1 = productDescriptionDAO.readByIdSerialNumber("1q2e1z611");
        orderDetails1.setSerialNumber(productDescription1);
        orderDetails1.setNumber(10);
        Order order1 = orderDAO.read(1);
        orderDetails1.setIdOrderDetails(order1);
        orderDetailsDAO.create(orderDetails1);

        OrderDetails orderDetails2 = new OrderDetails();
        ProductDescription productDescription2 = productDescriptionDAO.readByIdSerialNumber("23dw292");
        orderDetails2.setSerialNumber(productDescription2);
        orderDetails2.setNumber(20);
        Order order2 = orderDAO.read(1);
        orderDetails2.setIdOrderDetails(order2);
        orderDetailsDAO.create(orderDetails2);

        OrderDetails orderDetails3 = new OrderDetails();
        ProductDescription productDescription3 = productDescriptionDAO.readByIdSerialNumber("23dw292");
        orderDetails3.setSerialNumber(productDescription3);
        orderDetails3.setNumber(5);
        Order order3 = orderDAO.read(2);
        orderDetails3.setIdOrderDetails(order3);
        orderDetailsDAO.create(orderDetails3);

        OrderDetails orderDetails4 = new OrderDetails();
        ProductDescription productDescription4 = productDescriptionDAO.readByIdSerialNumber("3s1a027s");
        orderDetails4.setSerialNumber(productDescription4);
        orderDetails4.setNumber(2);
        Order order4 = orderDAO.read(3);
        orderDetails4.setIdOrderDetails(order4);
        orderDetailsDAO.create(orderDetails4);

        OrderDetails orderDetails5 = new OrderDetails();
        ProductDescription productDescription5 = productDescriptionDAO.readByIdSerialNumber("4s452d34");
        orderDetails5.setSerialNumber(productDescription5);
        orderDetails5.setNumber(35);
        Order order5 = orderDAO.read(3);
        orderDetails5.setIdOrderDetails(order5);
        orderDetailsDAO.create(orderDetails5);
    }
}
