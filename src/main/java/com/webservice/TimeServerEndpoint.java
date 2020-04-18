package com.webservice;

import javax.xml.ws.Endpoint;

public class TimeServerEndpoint {

    public void startServerTime() {
        Endpoint.publish("http://127.0.0.1:8081/time", new TimeServerImpl());
    }

}
