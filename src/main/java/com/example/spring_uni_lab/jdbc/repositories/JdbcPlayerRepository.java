package com.example.spring_uni_lab.jdbc.repositories;

import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.entities.Player;
import com.example.spring_uni_lab.entities.Statistics;
import com.example.spring_uni_lab.entities.Team;
import com.example.spring_uni_lab.jdbc.JdbcManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class JdbcPlayerRepository {
    private static final String SELECT_ALL_PLAYERS = "select * from player";
    private static final String UPDATE_PLAYER = "Update player set first_name = ?, last_name = ?, goals = ?, assists = ?, team_id = ? where id = ?";
    private static final String SELECT_BY_ID = "Select * from player where id = ?";
    private static final String INSERT_PLAYER = "INSERT INTO player (first_name, last_name, goals, assists, team_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE from player where id = ?";
    private final JdbcManager jdbcManager;

    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();

        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PLAYERS)) {
            ResultSet dbResult = preparedStatement.executeQuery();
            while (dbResult.next()) {
                players.add(Player.builder()
                        .firstName(dbResult.getString("first_name"))
                        .lastName(dbResult.getString("last_name"))
                                .team(Team.builder().id(dbResult.getLong("team_id")).build())
                                .statistics(Statistics.builder()
                                        .goals(dbResult.getInt("goals"))
                                        .assists(dbResult.getInt("assists"))
                                        .build())
                        .build());
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
        return players;
    }

    public void update(Player player) {
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAYER)) {
            preparedStatement.setString(1, player.getFirstName());
            preparedStatement.setString(2, player.getLastName());
            preparedStatement.setInt(3, player.getStatistics().getGoals());
            preparedStatement.setInt(4, player.getStatistics().getAssists());
            preparedStatement.setLong(5, player.getTeam().getId());
            preparedStatement.setLong(6, player.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
    }

    public Player findById(Long id) {
        Player player = null;

        try {
            Connection connection = jdbcManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet databaseResult = preparedStatement.executeQuery();

            while (databaseResult.next()) {
                player = Player.builder()
                        .id(databaseResult.getLong("id"))
                        .firstName(databaseResult.getString("first_name"))
                        .lastName(databaseResult.getString("last_name"))
                        .statistics(Statistics.builder()
                                .assists(databaseResult.getInt("assists"))
                                .goals(databaseResult.getInt("goals"))
                                .build())
                        .team(Team.builder()
                                .id(databaseResult.getLong("team_id"))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
        return player;
    }

    public void save(Player player) {
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PLAYER)) {
            preparedStatement.setString(1, player.getFirstName());
            preparedStatement.setString(2, player.getLastName());
            preparedStatement.setInt(3, player.getStatistics().getGoals());
            preparedStatement.setInt(4, player.getStatistics().getAssists());
            preparedStatement.setLong(5, player.getTeam().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
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