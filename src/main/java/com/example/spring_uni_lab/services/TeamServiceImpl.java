package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.EntityDtoMapper;
import com.example.spring_uni_lab.dto.TeamDto;
import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.entities.League;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.hibernate.repository.HbCoachRepository;
import com.example.spring_uni_lab.hibernate.repository.HbLeagueRepository;
import com.example.spring_uni_lab.hibernate.repository.HbTeamRepository;
import com.example.spring_uni_lab.jdbc.repositories.JdbcCoachRepository;
import com.example.spring_uni_lab.jdbc.repositories.JdbcLeagueRepository;
import com.example.spring_uni_lab.jdbc.repositories.JdbcTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final HbCoachRepository hbCoachRepository;
    private final HbLeagueRepository hbLeagueRepository;
    private final HbTeamRepository hbTeamRepository;
    private final JdbcTeamRepository jdbcTeamRepository;
    private final JdbcCoachRepository jdbcCoachRepository;
    private final JdbcLeagueRepository jdbcLeagueRepository;

    @Override
    public List<TeamDto> fetchTeamList() {
        List<Team> teamList = jdbcTeamRepository.findAll();

        return teamList.stream()
                .map(EntityDtoMapper::teamToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {

        Coach coach = jdbcCoachRepository.findById(teamDto.getCoach().getId());
        League league = jdbcLeagueRepository.findById(teamDto.getLeague().getId());

        Team team = EntityDtoMapper.teamToEntity(teamDto);
        team.setCoach(coach);
        team.setLeague(league);

        jdbcTeamRepository.save(team);
        return EntityDtoMapper.teamToDto(team);
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto, long id) {
        Team team = jdbcTeamRepository.findById(id);
        team.setName(teamDto.getName());

        if (teamDto.getCoach() != null && teamDto.getCoach().getId() != null) {
            Coach coach = jdbcCoachRepository.findById(teamDto.getCoach().getId());
            team.setCoach(coach);
        }

        if (teamDto.getLeague() != null && teamDto.getLeague().getId() != null) {
            League league = jdbcLeagueRepository.findById(teamDto.getLeague().getId());
            team.setLeague(league);
        }

        jdbcTeamRepository.update(team);
        return EntityDtoMapper.teamToDto(team);
    }

    @Override
    public TeamDto deleteTeamById(long id) {
        Team team = jdbcTeamRepository.findById(id);

        jdbcTeamRepository.deleteById(id);
        return EntityDtoMapper.teamToDto(team);
    }
}
