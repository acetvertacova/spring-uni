package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.entities.Team;

import java.util.List;

public interface TeamService {
    Team saveTeam(Team team);

    List<Team> fetchTeamList();

    Team updateTeam(Team team, long id);

    void deleteTeamById(long id);
}
