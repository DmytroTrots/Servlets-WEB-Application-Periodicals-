package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.rerository.PublisherDao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MysSQLPublisherDao implements PublisherDao, SQLQuery {
    @Override
    public Map<String, Integer> findAllPublishers(Connection con) throws SQLException {
        Map<String, Integer> publisherMap = new HashMap<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(GET_ALL_PUBLISHERS)) {
            while (resultSet.next()) {
                publisherMap.put(resultSet.getString("name"), resultSet.getInt("id"));
            }
        }
        return publisherMap;
    }

    @Override
    public Integer insertPublisher(String publisher, String telephone, Connection con) throws SQLException {
        Integer publisherId = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_PUBLISHER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, publisher);
            preparedStatement.setString(2, telephone);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    publisherId = resultSet.getInt(1);
                }
            }
        }
        return publisherId;
    }
}
