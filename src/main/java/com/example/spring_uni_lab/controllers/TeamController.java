package com.example.spring_uni_lab.controllers;

import com.example.spring_uni_lab.dto.TeamDto;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.services.TeamService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("teamApi")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/teams")
    public List<TeamDto> fetchTeamList() {
        return teamService.fetchTeamList().stream()
                .map(team -> modelMapper.map(team, TeamDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamDto> saveTeam(@RequestBody TeamDto teamDto){
        //Dto to entity
        Team teameRequest = modelMapper.map(teamDto, Team.class);
        Team team = teamService.saveTeam(teameRequest);

        // entity to DTO
        TeamDto teamResponse = modelMapper.map(team, TeamDto.class);
        return new ResponseEntity<TeamDto>(teamResponse, HttpStatus.CREATED);
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<TeamDto> updateTeam(@RequestBody TeamDto teamDto, @PathVariable("id") long id) {

        // DTO to Entity
        Team teamRequest = modelMapper.map(teamDto, Team.class);
        Team team = teamService.updateTeam(teamRequest, id);

        // entity to DTO
        TeamDto teamResponse = modelMapper.map(team, TeamDto.class);
        return ResponseEntity.ok().body(teamResponse);
    }

    @DeleteMapping("/players/{id}")
    public void deleteTeamById(@PathVariable("id") long id){
        teamService.deleteTeamById(id);
    }
}
