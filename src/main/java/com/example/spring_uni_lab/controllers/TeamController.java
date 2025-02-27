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

    @PostMapping("/teams")
    public TeamDto saveTeam(TeamDto teamDto){
        return teamService.saveTeam(teamDto);
    }

    @PutMapping("/teams/{id}")
    public TeamDto updateTeam(@RequestBody TeamDto teamDto, @PathVariable("id") long id) {
        return teamService.updateTeam(teamDto, id);
    }

    //does not delete by id!
    @DeleteMapping("/teams/{id}")
    public TeamDto deleteTeamById(@PathVariable("id") long id){
        return teamService.deleteTeamById(id);
    }
}
