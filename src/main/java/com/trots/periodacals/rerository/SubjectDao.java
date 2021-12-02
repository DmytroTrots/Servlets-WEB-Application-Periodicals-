package com.trots.periodacals.rerository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface SubjectDao {
    Map<String, Integer> findAllSubjects(Connection con) throws SQLException;

    Integer insertSubject(String subj, Connection con) throws SQLException;
}
