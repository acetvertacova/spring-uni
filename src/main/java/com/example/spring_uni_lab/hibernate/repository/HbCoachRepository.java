package com.example.spring_uni_lab.hibernate.repository;

import com.example.spring_uni_lab.entities.Coach;
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
public class HbCoachRepository {
    private final SessionFactory sessionFactory;
    public void save(Coach coach) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(coach);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }
    public Optional<Coach> findById(long id) {
        return Optional.of(sessionFactory.openSession().get(Coach.class, id));
    }
    public List<Coach> findAll() {
        String sql = "FROM Coach";
        Session session = sessionFactory.openSession();
        Query<Coach> query = session.createQuery(sql, Coach.class);
        return query.getResultList();
    }
    public void update(Coach coach) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(coach);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Coach coach = session.get(Coach.class, id);
        try {
            session.delete(coach);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }
}
