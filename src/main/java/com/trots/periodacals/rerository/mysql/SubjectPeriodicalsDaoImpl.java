package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.rerository.SubjectPeriodicalsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Subject periodicals dao.
 */
public class SubjectPeriodicalsDaoImpl implements SubjectPeriodicalsDao, SQLQuery {
    @Override
    public boolean insertSubjIdAndPeriodicalId(Integer subjId, Integer periodId, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_RECORD_INTO_PERIODICAL_HAS_SUBJECT)) {
            preparedStatement.setInt(1, periodId);
            preparedStatement.setInt(2, subjId);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public List<String> getSubjectOfPeriodById(Integer id, Connection con) throws SQLException {
        List<String> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_SUBJECT_OF_ONE_PERIODICAL_BY_ITS_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(resultSet.getString("themes_of_books"));
                }
            }
        }
        return list;
    }
}
