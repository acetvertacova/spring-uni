package com.example.spring_uni_lab.hibernate.repository;

import com.example.spring_uni_lab.entities.Player;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class HbPlayerRepository {
    private final SessionFactory sessionFactory;

    public List<Player> findAll() {
        String sql = "FROM Player";
        Session session = sessionFactory.openSession();
        Query<Player> query = session.createQuery(sql, Player.class);
        return query.getResultList();
    }
}