package com.example.spring_uni_lab.repositories;

import com.example.spring_uni_lab.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MatchRepository extends JpaRepository<Match, Long> {
}
