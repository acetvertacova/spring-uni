package com.example.spring_uni_lab.hibernate.repository;

import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.entities.League;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class HbLeagueRepository {
    private final SessionFactory sessionFactory;

    public void save(League league){
        Session session = sessionFactory.openSession();
        Transaction saveLeague = session.beginTransaction();
        session.save(league);
        saveLeague.commit();
        session.close();
    }

    public Optional<League> findById(long id){
        return Optional.of(sessionFactory.openSession().get(League.class, id));
    }
}
