package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.entities.Player;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;

    @Override
    public List<Team> fetchTeamList(){
        return teamRepository.findAll();
    }

    @Override
    public Team saveTeam(Team team){
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(@RequestBody Team team, @PathVariable long id){
        teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));

        Team updatedTeam = Team.builder()
                .id(team.getId())
                .name(team.getName())
                .coach(team.getCoach())
                .players(team.getPlayers())
                .matches(team.getMatches())
                .build();

        return teamRepository.save(updatedTeam);

    }

    @Override
    public void deleteTeamById(long id){
        teamRepository.deleteById(id);
    }
}
