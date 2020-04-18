package com.orders;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private static SessionFactory factory;

    @Bean
    public SessionFactory getFactory() {
        if (factory == null) {
            factory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
        }
        return factory;
    }


}
