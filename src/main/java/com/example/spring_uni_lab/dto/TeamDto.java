package com.example.spring_uni_lab.dto;

import com.example.spring_uni_lab.entities.Player;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private String name;
    private String coach;
    private List<PlayerDto> players;
    private Set<MatchDto> matches;

}
