package com.example.spring_uni_lab.controllers;

import com.example.spring_uni_lab.dto.TeamDto;
import com.example.spring_uni_lab.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("teamApi")
public class TeamController {

    private final TeamService teamService;


    @GetMapping("/teams")
    public List<TeamDto> fetchTeamList() {
        return teamService.fetchTeamList();
    }

    @PostMapping("/team")
    public TeamDto createTeam(@RequestBody TeamDto team) {
        return teamService.createTeam(team);
    }

    @PutMapping("/team/{id}")
    public TeamDto updateTeam(@RequestBody TeamDto teamDto, @PathVariable("id") long id) {
        return teamService.updateTeam(teamDto, id);
    }

    @DeleteMapping("/team/{id}")
    public TeamDto deleteById(@PathVariable("id") long id) {
        return teamService.deleteTeamById(id);
    }
}
