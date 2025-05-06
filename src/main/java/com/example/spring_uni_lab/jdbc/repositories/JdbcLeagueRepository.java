package com.example.spring_uni_lab.jdbc.repositories;

import com.example.spring_uni_lab.entities.Coach;
import com.example.spring_uni_lab.entities.League;
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
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class JdbcLeagueRepository {
    private static final String SELECT_BY_ID = "SELECT * from league where id = ?";
    private final JdbcManager jdbcManager;

    public League findById(Long id) {
        League league = null;

        try {
            Connection connection = jdbcManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet databaseResult = preparedStatement.executeQuery();

            while (databaseResult.next()) {
                league = League.builder()
                        .id(databaseResult.getLong("id"))
                        .name(databaseResult.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
        return league;
    }
}
