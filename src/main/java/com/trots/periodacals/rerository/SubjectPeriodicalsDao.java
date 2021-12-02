package com.trots.periodacals.rerository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SubjectPeriodicalsDao {
    boolean insertSubjIdAndPeriodicalId(Integer subjId, Integer periodId, Connection con) throws SQLException;

    List<String> getSubjectOfPeriodById(Integer id, Connection con) throws SQLException;
}
