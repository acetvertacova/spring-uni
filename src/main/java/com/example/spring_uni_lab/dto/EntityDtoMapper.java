package com.example.spring_uni_lab.dto;


import com.example.spring_uni_lab.entities.*;

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
                .toList()
                : null;

    }

    public static List<PlayerDto> playerListToDto(List<Player> playerList) {
        return playerList != null ? playerList.stream()
                .map(player -> playerToDto(player))
                .toList()
                : null;

    }

    public static TeamDto teamToDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .players(playerListToDto(team.getPlayers()))
                .coach(coachtoDto(team.getCoach()))
                .league(leagueToDto(team.getLeague()))
                .matches(matchListToDto(team.getMatches()))
                .build();
    }

    public static CoachDto coachtoDto(Coach coach) {
        return CoachDto.builder()
                .id(coach.getId())
                .firstName(coach.getFirstName())
                .lastName(coach.getLastName())
                .build();
    }

    public static Coach coachtoEntity(CoachDto coachDto) {
        return Coach.builder()
                .firstName(coachDto.getFirstName())
                .lastName(coachDto.getLastName())
                .build();
    }

    public static LeagueDto leagueToDto(League league) {
        return LeagueDto.builder()
                .id(league.getId())
                .name(league.getName())
                .build();
    }

    public static Team teamToEntity(TeamDto teamDto) {
        return Team.builder()
                .name(teamDto.getName())
//                .coach(coachtoEntity(teamDto.getCoach()))
//                .players(playerListToEntity(teamDto.getPlayers()))
//                .matches(matchListToEntity(teamDto.getMatches()))
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
