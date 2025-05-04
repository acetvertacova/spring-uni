package com.example.spring_uni_lab.jdbc.repositories;

import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.entities.League;
import com.example.spring_uni_lab.entities.Team;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class JdbcTeamRepository {
    private final SessionFactory sessionFactory;

    public Team save(Team team) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Coach managedCoach = session.merge(team.getCoach());
            League managedLeague = session.merge(team.getLeague());

            team.setCoach(managedCoach);
            team.setLeague(managedLeague);

            Team merged = session.merge(team);
            tx.commit();
            return merged;
        }
    }
}