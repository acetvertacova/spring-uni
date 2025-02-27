package com.example.spring_uni_lab.repositories;

import com.example.spring_uni_lab.entities.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
