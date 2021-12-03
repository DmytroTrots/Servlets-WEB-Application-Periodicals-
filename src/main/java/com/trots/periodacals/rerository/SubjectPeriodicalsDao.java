package com.trots.periodacals.rerository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Subject periodicals dao.
 */
public interface SubjectPeriodicalsDao {
    /**
     * Insert subj id and periodical id boolean.
     *
     * @param subjId   the subj id
     * @param periodId the period id
     * @param con      the con
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean insertSubjIdAndPeriodicalId(Integer subjId, Integer periodId, Connection con) throws SQLException;

    /**
     * Gets subject of period by id.
     *
     * @param id  the id
     * @param con the con
     * @return the subject of period by id
     * @throws SQLException the sql exception
     */
    List<String> getSubjectOfPeriodById(Integer id, Connection con) throws SQLException;
}
