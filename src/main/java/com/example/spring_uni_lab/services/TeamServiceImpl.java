package com.example.spring_uni_lab.services;

import com.example.spring_uni_lab.dto.EntityDtoMapper;
import com.example.spring_uni_lab.dto.TeamDto;
import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.entities.League;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.hibernate.repository.HbCoachRepository;
import com.example.spring_uni_lab.hibernate.repository.HbLeagueRepository;
import com.example.spring_uni_lab.hibernate.repository.HbTeamRepository;
import com.example.spring_uni_lab.repositories.CoachRepository;
import com.example.spring_uni_lab.repositories.LeagueRepository;
import com.example.spring_uni_lab.repositories.TeamRepository;
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

    @Override
    public List<TeamDto> fetchTeamList() {
        List<Team> teamList = hbTeamRepository.findAll();

        return teamList.stream()
                .map(EntityDtoMapper::teamToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {

        Coach coach = hbCoachRepository.findById(teamDto.getCoach().getId()).orElseThrow(() -> new RuntimeException("Coach not found"));
        League league = hbLeagueRepository.findById(teamDto.getLeague().getId()).orElseThrow(() -> new RuntimeException("Coach not found"));

        Team team = EntityDtoMapper.teamToEntity(teamDto);
        team.setCoach(coach);
        team.setLeague(league);

        hbTeamRepository.save(team);
        return EntityDtoMapper.teamToDto(team);
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto, long id) {
        Team team = hbTeamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));

        team.setName(teamDto.getName());

        if (teamDto.getCoach() != null && teamDto.getCoach().getId() != null) {
            Coach coach = hbCoachRepository.findById(teamDto.getCoach().getId())
                    .orElseThrow(() -> new RuntimeException("Coach not found with id: " + teamDto.getCoach().getId()));
            team.setCoach(coach);
        }

        if (teamDto.getLeague() != null && teamDto.getLeague().getId() != null) {
            League league = hbLeagueRepository.findById(teamDto.getLeague().getId())
                    .orElseThrow(() -> new RuntimeException("League not found with id: " + teamDto.getLeague().getId()));
            team.setLeague(league);
        }

        hbTeamRepository.save(team);
        return EntityDtoMapper.teamToDto(team);
    }

    @Override
    public TeamDto deleteTeamById(long id) {
        Team team = hbTeamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        hbTeamRepository.deleteById(id);
        return EntityDtoMapper.teamToDto(team);
    }
}
