package com.example.spring_uni_lab.dto;

import com.example.spring_uni_lab.entities.Match;
import com.example.spring_uni_lab.entities.Player;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private long id;
    private String name;
    private String coach;
    private List<Player> players;
    private Set<Match> matches;

}
