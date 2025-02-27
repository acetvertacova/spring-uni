package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.EntityDtoMapper;
import com.example.spring_uni_lab.dto.TeamDto;
import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.entities.League;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.repositories.CoachRepository;
import com.example.spring_uni_lab.repositories.LeagueRepository;
import com.example.spring_uni_lab.repositories.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final CoachRepository coachRepository;
    private final LeagueRepository leagueRepository;

    @Override
    public List<TeamDto> fetchTeamList() {
        List<Team> teamList = teamRepository.findAll();

        return teamList.stream()
                .map(EntityDtoMapper::teamToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto saveTeam(TeamDto teamDto) {

        Coach coach = coachRepository.findById(teamDto.getCoach().getId()).orElseThrow(() -> new RuntimeException("Coach not found"));
        League league = leagueRepository.findById(teamDto.getLeague().getId()).orElseThrow(() -> new RuntimeException("Coach not found"));

        Team team = EntityDtoMapper.teamToEntity(teamDto);
        team.setCoach(coach);
        team.setLeague(league);

        teamRepository.save(team);
        return EntityDtoMapper.teamToDto(team);
    }

    @Override
    public TeamDto updateTeam(@RequestBody TeamDto teamDto, @PathVariable long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));

        team.setName(teamDto.getName());
        //team.setPlayers(playerListToEntity(teamDto.getPlayers()));
        //team.setCoach(coachtoEntity(teamDto.getCoach()));
        //team.setMatches(matchListToEntity(teamDto.getMatches()));

        teamRepository.save(team);
        return EntityDtoMapper.teamToDto(team);
    }

    @Override
    public TeamDto deleteTeamById(long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        teamRepository.deleteById(team.getId());
        return EntityDtoMapper.teamToDto(team);
    }
}
