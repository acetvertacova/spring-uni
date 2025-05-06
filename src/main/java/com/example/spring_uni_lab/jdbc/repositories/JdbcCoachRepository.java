package com.example.spring_uni_lab.jdbc.repositories;

import com.example.spring_uni_lab.entities.Coach;
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
public class JdbcCoachRepository {
    private static final String SELECT_ALL_COACHES = "select * from coach";
    private static final String INSERT_COACH = "INSERT INTO coach (first_name, last_name) VALUES (?, ?)";
    private static final String UPDATE_COACH = "UPDATE coach SET first_name = ?, last_name = ? where id = ?";
    private static final String SELECT_BY_ID = "SELECT * from coach where id = ?";
    private static final String DELETE = "DELETE from coach where id = ?";

    private final JdbcManager jdbcManager;

    public List<Coach> findAll() {
        List<Coach> coaches = new ArrayList<>();

        try {
            Connection connection = jdbcManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COACHES);
            ResultSet databaseResult = preparedStatement.executeQuery();

            while (databaseResult.next()) {
                coaches.add(Coach.builder()
                        .id(databaseResult.getLong("id"))
                        .firstName(databaseResult.getString("first_name"))
                        .lastName(databaseResult.getString("last_name"))
                        .build());
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
        return coaches;
    }

    public void save(Coach coach) {
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COACH)) {
            preparedStatement.setString(1, coach.getFirstName());
            preparedStatement.setString(2, coach.getLastName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
    }

    public void update(Coach coach) {
        try (Connection connection = jdbcManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COACH)) {
            preparedStatement.setString(1, coach.getFirstName());
            preparedStatement.setString(2, coach.getLastName());
            preparedStatement.setLong(3, coach.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
    }

    public Coach findById(Long id) {
        Coach coach = null;

        try {
            Connection connection = jdbcManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet databaseResult = preparedStatement.executeQuery();

            while (databaseResult.next()) {
                coach = Coach.builder()
                        .id(databaseResult.getLong("id"))
                        .firstName(databaseResult.getString("first_name"))
                        .lastName(databaseResult.getString("last_name"))
                        .build();
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
        return coach;
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

