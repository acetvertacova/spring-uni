package com.example.spring_uni_lab.repositories;

import com.example.spring_uni_lab.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachRepository extends JpaRepository<Coach, Long> {
}
