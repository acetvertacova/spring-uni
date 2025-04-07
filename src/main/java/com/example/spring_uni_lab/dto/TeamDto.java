package com.example.spring_uni_lab.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private Long id;
    private String name;
    private CoachDto coach;
    private LeagueDto league;
    private List<PlayerDto> players;
    private Set<MatchDto> matches;
}
