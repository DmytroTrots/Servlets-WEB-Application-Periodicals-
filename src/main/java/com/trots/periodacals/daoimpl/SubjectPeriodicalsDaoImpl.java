package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dao.SubjectPeriodicalsDao;
import com.trots.periodacals.dbconnection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SubjectPeriodicalsDaoImpl {

    SubjectPeriodicalsDao subjectPeriodicalsDao = new SubjectPeriodicalsDao();

    public boolean insertSubjectIdAndPeriodicalIdIntoDB(Integer subjId, Integer periodId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            return subjectPeriodicalsDao.insertSubjIdAndPeriodicalId(subjId, periodId, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<String> findAllSubjectOfPeriodicalById(Integer id){
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            return subjectPeriodicalsDao.getSubjectOfPeriodById(id, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }
}
