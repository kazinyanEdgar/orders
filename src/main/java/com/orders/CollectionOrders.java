package com.orders;

import com.orders.dao.OrderDAO;
import com.orders.dao.OrderDetailsDAO;
import com.orders.dao.ProductDescriptionDAO;
import com.orders.model.Order;
import com.orders.model.OrderDetails;
import com.orders.model.ProductDescription;
import com.webservice.TimeServer;
import com.webservice.TimeServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Named
public class CollectionOrders {

    private String time;

    public String getTime() throws MalformedURLException {
        URL url = new URL("http://127.0.0.1:8081/time?wsdl");
        QName qname = new QName("http://webservice.com/", "TimeServerImplService");
        Service service = Service.create(url, qname);
        TimeServer dateTime = service.getPort(TimeServer.class);
        return dateTime.getTime();
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderDetailsDAO orderDetailsDAO;

    @Autowired
    private ProductDescriptionDAO productDescriptionDAO;

    private List<Order> orderList;

    private Map<Order, List<ProductDescription>> map = new LinkedHashMap<>();

    @Autowired
    private FillTablesFromXml fillTablesFromXml;


    public Map<Order, List<ProductDescription>> getMap() {
        return map;
    }

    public void setMap(Map<Order, List<ProductDescription>> map) {
        this.map = map;
    }

    @PostConstruct
    public void show() {
        new TimeServerEndpoint().startServerTime();
        orderList = orderDAO.getAllTheOrders();
        for (int i = 0; i < orderList.size(); i++) {
            map.put(orderList.get(i), null);
        }
    }

    public void getOrdersDetails() {
        fillTablesFromXml.parseXml();
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            List<OrderDetails> orderDetailsList = orderDetailsDAO.readByIdOrder(order);
            List<ProductDescription> listProducts = getProductsDescription(orderDetailsList);
            map.put(order, listProducts);
        }
    }

    private List<ProductDescription> getProductsDescription(List<OrderDetails> orderDetailsList) {
        List<ProductDescription> listProducts = new ArrayList<>();
        for (int i = 0; i < orderDetailsList.size(); i++) {
            ProductDescription productDescription = productDescriptionDAO.readByIdSerialNumber(
                    String.valueOf(orderDetailsList.get(i).getSerialNumber().getIdSerialNumber()));
            listProducts.add(productDescription);
        }
        return listProducts;
    }

}
