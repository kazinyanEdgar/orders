package com.webservice;


import javax.jws.WebService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebService(endpointInterface = "com.webservice.TimeServer")
public class TimeServerImpl implements TimeServer {

    public String getTime() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formatDateTime = date.format(formatter);
        return formatDateTime;
    }

}