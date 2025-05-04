package com.example.spring_uni_lab.hibernate.repository;

import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.hibernate.SessionManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class HbCoachRepository {
    private final SessionFactory sessionFactory;

    public void save(Coach coach){
        Session session = sessionFactory.openSession();
        Transaction saveCoach = session.beginTransaction();
        session.save(coach);
        saveCoach.commit();
        session.close();
    }

    public Optional<Coach> findById(long id){
        return Optional.of(sessionFactory.openSession().get(Coach.class, id));
    }
}
