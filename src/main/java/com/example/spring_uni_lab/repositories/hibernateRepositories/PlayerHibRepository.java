package com.example.spring_uni_lab.repositories.hibernateRepositories;

import com.example.spring_uni_lab.entities.Player;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerHibRepository {

    private final SessionFactory sessionFactory;

    public List<Player> findAll(){

        var session = sessionFactory.openSession();
        var players = session.createQuery("FROM Player", Player.class).getResultList();

        session.close();

        return players;
    }
}
