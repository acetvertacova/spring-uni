package com.example.spring_uni_lab.repositories;

import com.example.spring_uni_lab.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByName(String name);
}
