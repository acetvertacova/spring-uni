package com.example.spring_uni_lab.dto;


import com.example.spring_uni_lab.entities.Match;
import com.example.spring_uni_lab.entities.Player;
import com.example.spring_uni_lab.entities.Statistics;
import com.example.spring_uni_lab.entities.Team;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class EntityDtoMapper {

    public static PlayerDto playerToDto(Player player) {
        return PlayerDto.builder()
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .teamId(player.getTeam().getId())
                .statistics(Statistics.builder().goals(player.getStatistics().getGoals())
                        .assists(player.getStatistics().getAssists())
                        .build())
                .build();
    }

    public static Player playerToEntity(PlayerDto playerDto) {
        return Player.builder()
                .firstName(playerDto.getFirstName())
                .lastName(playerDto.getLastName())
                .statistics(Statistics.builder().goals(playerDto.getStatistics().getGoals())
                        .assists(playerDto.getStatistics().getAssists())
                        .build())
                .build();
    }

    public static List<Player> playerListToEntity(List<PlayerDto> playerListDto) {
        return playerListDto != null ? playerListDto.stream()
                .map(playerDto -> playerToEntity(playerDto))
                .toList() : null;

    }

    public static List<PlayerDto> playerListToDto(List<Player> playerList) {
        return playerList != null ? playerList.stream()
                .map(player -> playerToDto(player))
                .toList() : null;

    }

    public static TeamDto teamToDto(Team team) {
        return TeamDto.builder()
                .name(team.getName())
                //.coach(team.getCoach().getLastName())
                .players(playerListToDto(team.getPlayers()))
                .matches(matchListToDto(team.getMatches()))
                .build();
    }

    public static Team teamToEntity(TeamDto teamDto) {
        return Team.builder()
                .name(teamDto.getName())
                .players(playerListToEntity(teamDto.getPlayers()))
                .matches(teamDto.getMatches().stream()
                        .map(match -> matchToEntity(match))
                        .collect(Collectors.toSet()))
                .build();
    }

    public static MatchDto matchToDto(Match match) {
        return MatchDto.builder()
                .title(match.getTitle())
                .date(match.getDate())
                .build();
    }

    public static Match matchToEntity(MatchDto matchDto) {
        return Match.builder()
                .title(matchDto.getTitle())
                .date(matchDto.getDate())
                .build();
    }

    public static Set<Match> matchListToEntity(Set<MatchDto> matchListDto) {
        return matchListDto != null ? matchListDto.stream()
                .map(matchDto -> matchToEntity(matchDto))
                .collect(Collectors.toSet()) : null;
    }

    public static Set<MatchDto> matchListToDto(Set<Match> matchList) {
        return matchList != null ? matchList.stream()
                .map(match -> matchToDto(match))
                .collect(Collectors.toSet()) : null;

    }
}
