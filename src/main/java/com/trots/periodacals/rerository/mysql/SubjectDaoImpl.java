package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.rerository.SubjectDao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Subject dao.
 */
public class SubjectDaoImpl implements SubjectDao, SQLQuery {
    @Override
    public Map<String, Integer> findAllSubjects(Connection con) throws SQLException {
        Map<String, Integer> subjectMap = new HashMap<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(GET_ALL_SUBJECTS)) {
            while (resultSet.next()) {
                subjectMap.put(resultSet.getString("themes_of_books"), resultSet.getInt("id"));
            }
        }
        return subjectMap;
    }

    @Override
    public Integer insertSubject(String subj, Connection con) throws SQLException {
        Integer subjectId = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_SUBJECT_INTO_DB, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subj);
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    subjectId = resultSet.getInt(1);
                }

            }
        }
        return subjectId;
    }
}
