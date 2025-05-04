package com.example.spring_uni_lab.hibernate.repository;

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
public class HbTeamRepository {
    private final SessionFactory sessionFactory;
    private final HbCoachRepository coachRepository;
    private final HbLeagueRepository hbLeagueRepository;

    public Team save(Team team) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();

            Coach managedCoach = (Coach) session.merge(team.getCoach());
            League managedLeague = (League) session.merge(team.getLeague());

            team.setCoach(managedCoach);
            team.setLeague(managedLeague);

            Team merged = (Team) session.merge(team);
            tx.commit();
            return merged;
        }

    }
}
