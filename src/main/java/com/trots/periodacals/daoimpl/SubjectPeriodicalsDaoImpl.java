package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SubjectPeriodicalsDaoImpl {

    private static final Logger log = LogManager.getLogger(SubjectPeriodicalsDaoImpl.class);

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
            log.error("Cannot insert into DB periodical and subjects");
        }
        return false;
    }

    public List<String> findAllSubjectOfPeriodicalById(Integer id){
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getSubjectOfPeriodById(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get all subjects of periodical by id");
        }
        return Collections.emptyList();
    }
}
