package com.example.spring_uni_lab.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

public class SessionManager {
    @Bean
    public SessionFactory provideSessionFactory()
    {
        Configuration config = new Configuration();
        config.configure("resources/hibernate.cfg.xml");

        return config.buildSessionFactory();
    }
}
