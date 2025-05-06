package com.example.spring_uni_lab.jdbc.repositories;

import com.example.spring_uni_lab.entities.*;
import com.example.spring_uni_lab.jdbc.JdbcManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class JdbcTeamRepository {
    private static final String INSERT_TEAM = "INSERT INTO team (name, coach_id , league_id) values (?, ?, ?)";
    private static final String SELECT_ALL_TEAM = "select t.id as id, t.name, c.id as coach_id, c.first_name as c_first_name, c.last_name as c_last_name, l.id as league_id, l.\"name\" as league_name, m.title, m.\"date\" from team t \n" +
            "left join coach c on t.coach_id = c.id\n" +
            "left join league l on t.league_id = l.id\n" +
            "left join \"match\" m on t.id = m.home_team_id OR t.id = m.away_team_id";
    private static final String SELECT_PLAYERS = "Select * from player";
    private static final String UPDATE_TEAM = "UPDATE team SET name = ?, coach_id = ?, league_id = ? WHERE id = ?";
    private static final String SELECT_TEAM_BY_ID = "select * from team t\n" +
            "LEFT join player p on t.id = p.team_id\n" +
            "left join \"match\" m on t.id = m.home_team_id or t.id = m.away_team_id \n" +
            "where t.id = ?";
    private static final String DELETE = "Delete from team where id = ?";
    private final JdbcManager jdbcManager;

    public void save(Team team) {
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEAM)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setLong(2, team.getCoach().getId());
            preparedStatement.setLong(3, team.getLeague().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
    }

    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        List<Player> players = new ArrayList<>();

        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEAM)) {
            ResultSet dbResult = preparedStatement.executeQuery();

            while (dbResult.next()) {
                teams.add(Team.builder()
                        .id(dbResult.getLong("id"))
                        .name(dbResult.getString("name"))
                        .coach(Coach.builder()
                                .id(dbResult.getLong("coach_id"))
                                .firstName(dbResult.getString("c_first_name"))
                                .lastName(dbResult.getString("c_last_name"))
                                .build())
                        .players(new ArrayList<>())
                        .league(League.builder()
                                .id(dbResult.getLong("league_id"))
                                .name(dbResult.getString("league_name"))
                                .build())
                        .matches(Set.of(Match.builder()
                                .title(dbResult.getString("title"))
                                .date(dbResult.getDate("date"))
                                .build()))
                        .build());
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }

        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement playerStmt = connection.prepareStatement(SELECT_PLAYERS)) {
            ResultSet result = playerStmt.executeQuery();

            while (result.next()) {
                players.add(Player.builder()
                        .id(result.getLong("id"))
                        .firstName(result.getString("first_name"))
                        .lastName(result.getString("last_name"))
                        .statistics(Statistics.builder()
                                .goals(result.getInt("goals"))
                                .assists(result.getInt("assists")).build())
                        .team(Team.builder().id(result.getLong("team_id")).build())
                        .build());
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }

        for (Team team : teams) {
            for (Player player : players) {
                if (player.getTeam().getId() == team.getId()) {
                    team.getPlayers().add(player);
                }
            }
        }
        return teams;
    }

    public void update(Team team) {
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEAM)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setLong(2, team.getCoach().getId());
            preparedStatement.setLong(3, team.getLeague().getId());
            preparedStatement.setLong(4, team.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
    }

    public Team findById(Long id) {
        Team team = null;
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEAM_BY_ID)) {
            preparedStatement.setLong(1, id);

            ResultSet dbResult = preparedStatement.executeQuery();

            if (dbResult.next()) {
                team = Team.builder()
                        .id(dbResult.getLong("id"))
                        .name(dbResult.getString("name"))
                        .coach(Coach.builder().id(dbResult.getLong("coach_id")).build())
                        .league(League.builder().id(dbResult.getLong("league_id")).build())
                        .matches(Set.of(Match.builder()
                                .title(dbResult.getString("title"))
                                .date(dbResult.getDate("date"))
                                .build()))
                        .players(new ArrayList<>())
                        .build();
            }
            Player player = Player.builder()
                    .team(Team.builder().id(id).build())
                    .firstName(dbResult.getString("first_name"))
                    .lastName(dbResult.getString("last_name"))
                    .statistics(Statistics.builder()
                            .goals(dbResult.getInt("goals"))
                            .assists(dbResult.getInt("assists"))
                            .build())
                    .build();

            team.getPlayers().add(player);
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }

        return team;
    }

    public void deleteById(Long id) {
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
    }
}