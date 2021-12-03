package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.rerository.SubjectPeriodicalsDao;
import com.trots.periodacals.rerository.mysql.DaoImplFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * The type Subject periodical service.
 */
public class SubjectPeriodicalService {
    private static final Logger log = LogManager.getLogger(SubjectPeriodicalService.class);

    private static SubjectPeriodicalService instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized SubjectPeriodicalService getInstance() {
        if (instance == null) {
            instance = new SubjectPeriodicalService();
        }
        return instance;
    }

    /**
     * The Repository.
     */
    SubjectPeriodicalsDao repository = new DaoImplFactory().getSubjectPeriodicalDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Insert subject id and periodical id into db boolean.
     *
     * @param subjId   the subj id
     * @param periodId the period id
     * @return the boolean
     */
    public boolean insertSubjectIdAndPeriodicalIdIntoDB(Integer subjId, Integer periodId) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.insertSubjIdAndPeriodicalId(subjId, periodId, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert into DB periodical and subjects");
        }
        return false;
    }

    /**
     * Find all subject of periodical by id list.
     *
     * @param id the id
     * @return the list
     */
    public List<String> findAllSubjectOfPeriodicalById(Integer id){
        try (Connection con = connectionPool.getConnection()) {
            return repository.getSubjectOfPeriodById(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get all subjects of periodical by id");
        }
        return Collections.emptyList();
    }
}
