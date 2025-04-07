package com.example.spring_uni_lab.config;

import com.example.spring_uni_lab.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateConf {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("show-sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");

        configuration.addAnnotatedClass(Coach.class);
        configuration.addAnnotatedClass(League.class);
        configuration.addAnnotatedClass(Match.class);
        configuration.addAnnotatedClass(Player.class);
        configuration.addAnnotatedClass(Team.class);

        StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(serviceRegistry.build());
    }
}