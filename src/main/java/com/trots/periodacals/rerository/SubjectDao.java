package com.trots.periodacals.rerository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * The interface Subject dao.
 */
public interface SubjectDao {
    /**
     * Find all subjects map.
     *
     * @param con the con
     * @return the map
     * @throws SQLException the sql exception
     */
    Map<String, Integer> findAllSubjects(Connection con) throws SQLException;

    /**
     * Insert subject integer.
     *
     * @param subj the subj
     * @param con  the con
     * @return the integer
     * @throws SQLException the sql exception
     */
    Integer insertSubject(String subj, Connection con) throws SQLException;
}
