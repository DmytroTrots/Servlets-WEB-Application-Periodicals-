package com.trots.periodacals.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectPeriodicalsDao {
    public boolean insertSubjIdAndPeriodicalId(Integer subjId, Integer periodId, Connection con) throws SQLException {
        try(PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO periodical_has_subject " +
                "(periodical_id, subject_id) VALUES (?,?)")){
            preparedStatement.setInt(1, periodId);
            preparedStatement.setInt(2, subjId);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    public List<String> getSubjectOfPeriodById(Integer id, Connection con) throws SQLException {
        List<String> list = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("Select `subject`.`themes_of_books` " +
                "from periodical_has_subject\n" +
                "Inner join `subject` on subject_id=`subject`.id\n" +
                "where periodical_has_subject.periodical_id = ?")){
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    list.add(resultSet.getString("themes_of_books"));
                }
            }
        }
        return list;
    }
}
