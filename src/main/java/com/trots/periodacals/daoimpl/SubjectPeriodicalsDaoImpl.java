package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SubjectPeriodicalsDaoImpl {

    private static SubjectPeriodicalsDaoImpl instance;

    public static synchronized SubjectPeriodicalsDaoImpl getInstance() {
        if (instance == null) {
            instance = new SubjectPeriodicalsDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public boolean insertSubjectIdAndPeriodicalIdIntoDB(Integer subjId, Integer periodId) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.insertSubjIdAndPeriodicalId(subjId, periodId, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<String> findAllSubjectOfPeriodicalById(Integer id){
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getSubjectOfPeriodById(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }
}
