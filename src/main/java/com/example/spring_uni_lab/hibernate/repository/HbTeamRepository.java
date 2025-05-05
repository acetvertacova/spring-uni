package com.example.spring_uni_lab.hibernate.repository;

import com.example.spring_uni_lab.entities.Team;
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
public class HbTeamRepository {
    private final SessionFactory sessionFactory;

    public List<Team> findAll() {
        String sql = "FROM Team";
        try (Session session = sessionFactory.openSession()) {
            Query<Team> query = session.createQuery(sql, Team.class);
            return query.getResultList();
        }
    }

    public void save(Team team) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.merge(team);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        session.close();
    }


    public void deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Team team = session.get(Team.class, id);

            if (team == null) {
                throw new RuntimeException("Team not found with id: " + id);
            }

            if (team.getCoach() != null) {
                team.getCoach().setTeam(null);
            }

            if (team.getLeague() != null) {
                team.getLeague().getTeams().remove(team);
            }

            try {
                session.delete(team);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    public Optional<Team> findById(long id) {
        return Optional.of(sessionFactory.openSession().get(Team.class, id));
    }
}
