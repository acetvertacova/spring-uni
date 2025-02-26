package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.TeamDto;
import java.util.List;

public interface TeamService {
    TeamDto saveTeam(TeamDto team);

    List<TeamDto> fetchTeamList();

    TeamDto updateTeam(TeamDto team, long id);

    TeamDto deleteTeamById(long id);
}
