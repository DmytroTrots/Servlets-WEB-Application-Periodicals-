package com.trots.periodacals.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PublisherDao {

    public Map<String, Integer> findAllPublishers(Connection con) throws SQLException {
        Map<String, Integer> publisherMap = new HashMap<>();
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM publisher")){
            while (resultSet.next()){
                publisherMap.put(resultSet.getString("name"), resultSet.getInt("id"));
            }
        }
        return publisherMap;
    }

    public Integer insertPublisher(String publisher, String telephone, Connection con) throws SQLException {
        Integer publisherId = null;
        try(PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `dbperiodicals`.`publisher` " +
                "(`name`, `telephone_number`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, publisher);
            preparedStatement.setString(2, telephone);
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()){
                    publisherId = resultSet.getInt(1);
                }
            }
        }
        return publisherId;
    }
}
