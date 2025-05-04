package com.example.spring_uni_lab.jdbc.repositories;

import com.example.spring_uni_lab.entities.Coach;
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
public class JdbcCoachRepository {
    private static final String SELECT_ALL = "SELECT * FROM Coach";
    private static final String SELECT_ALL_WITH_TEAM = "select c.id, c.first_name, c.last_name, t.id as team_id, t.\"name\"  from coach as c left join team  as t on c.id = t.coach_id;";

    private final JdbcManager jdbcManager;

    public List<Coach> findAll() {
        List<Coach> result = new ArrayList<>();
        try {
            Connection connection = jdbcManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WITH_TEAM);
            ResultSet databaseResult = preparedStatement.executeQuery();

            while (databaseResult.next()) {
                result.add(Coach.builder()
                                .id(databaseResult.getLong("id"))
                                .firstName(databaseResult.getString("first_name"))
                                .lastName(databaseResult.getString("last_name"))
                                .team(Team.builder()
                                          .id(databaseResult.getLong("team_id"))
                                          .name(databaseResult.getString("name"))
                                          .build())
                                .build());
            }
        } catch (SQLException e) {
            jdbcManager.printSQLException(e);
        }
        return result;
    }
}

