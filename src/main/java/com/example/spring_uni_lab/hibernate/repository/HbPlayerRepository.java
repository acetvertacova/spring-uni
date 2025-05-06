package com.example.spring_uni_lab.hibernate.repository;

import com.example.spring_uni_lab.entities.Player;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public void save(Player player) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(player);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }

    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Player player = session.get(Player.class, id);

        try {
            session.delete(player);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }

    public Optional<Player> findById(long id) {
        return Optional.of(sessionFactory.openSession().get(Player.class, id));
    }
}