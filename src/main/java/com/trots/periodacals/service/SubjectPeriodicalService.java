package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.rerository.SubjectPeriodicalsDao;
import com.trots.periodacals.rerository.UserDao;
import com.trots.periodacals.rerository.mysql.MySQLDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class SubjectPeriodicalService {
    private static final Logger log = LogManager.getLogger(SubjectPeriodicalService.class);

    private static SubjectPeriodicalService instance;

    public static synchronized SubjectPeriodicalService getInstance() {
        if (instance == null) {
            instance = new SubjectPeriodicalService();
        }
        return instance;
    }

    SubjectPeriodicalsDao repository = new MySQLDaoFactory().getSubjectPeriodicalDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public boolean insertSubjectIdAndPeriodicalIdIntoDB(Integer subjId, Integer periodId) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.insertSubjIdAndPeriodicalId(subjId, periodId, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert into DB periodical and subjects");
        }
        return false;
    }

    public List<String> findAllSubjectOfPeriodicalById(Integer id){
        try (Connection con = connectionPool.getConnection()) {
            return repository.getSubjectOfPeriodById(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get all subjects of periodical by id");
        }
        return Collections.emptyList();
    }
}
