package com.trots.periodacals.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class SubjectDao {

    public Map<String, Integer> findAllSubjects(Connection con) throws SQLException {
        Map<String, Integer> subjectMap = new HashMap<>();
        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM subject")){
            while (resultSet.next()){
                subjectMap.put(resultSet.getString("themes_of_books"), resultSet.getInt("id"));
            }
        }
        return subjectMap;
    }

    public Integer insertSubject(String subj, Connection con) throws SQLException {
        Integer subjectId = null;
        try(PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `dbperiodicals`.`subject` " +
                "(`themes_of_books`) VALUES (?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, subj);
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if(resultSet.next()){
                    subjectId = resultSet.getInt(1);
                }

            }
        }
        return subjectId;
    }

}
