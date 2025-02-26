package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.EntityDtoMapper;
import com.example.spring_uni_lab.dto.TeamDto;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.repositories.CoachRepository;
import com.example.spring_uni_lab.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;

    @Override
    public List<TeamDto> fetchTeamList(){
        List<Team> teamList = teamRepository.findAll();

        return teamList.stream()
                .map(EntityDtoMapper::teamToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto saveTeam(TeamDto teamDto){
        Team team = EntityDtoMapper.teamToEntity(teamDto);
        //todo set Coach
        teamRepository.save(team);
        return teamDto;
    }

    @Override
    public TeamDto updateTeam(@RequestBody TeamDto teamDto, @PathVariable long id){
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));

        team.setName(teamDto.getName());
       //team.setCoach(teamDto.getCoach());
        team.setPlayers(teamDto.getPlayers().stream()
                .map(EntityDtoMapper::playerToEntity)
                .collect(Collectors.toList()));

        team.setMatches(teamDto.getMatches().stream()
                .map(EntityDtoMapper::matchToEntity)
                .collect(Collectors.toSet()));

        teamRepository.save(team);

        return EntityDtoMapper.teamToDto(team);
    }

    @Override
    public TeamDto deleteTeamById(long id){
        Team team = teamRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Player not found"));

        teamRepository.deleteById(team.getId());

        return EntityDtoMapper.teamToDto(team);
    }
}
