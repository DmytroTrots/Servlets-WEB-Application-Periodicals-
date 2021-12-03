package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.rerository.SubjectDao;
import com.trots.periodacals.rerository.mysql.DaoImplFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

/**
 * The type Subject service.
 */
public class SubjectService {
    private static final Logger log = LogManager.getLogger(SubjectService.class);

    private static SubjectService instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }
        return instance;
    }

    /**
     * The Repository.
     */
    SubjectDao repository = new DaoImplFactory().getSubjectDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Find all subjects from db map.
     *
     * @return the map
     */
    public Map<String, Integer> findAllSubjectsFromDB(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findAllSubjects(con);
        } catch (SQLException throwables) {
            log.error("Cannot get all subjects");
        }
        return Collections.emptyMap();
    }

    /**
     * Insert subject into db integer.
     *
     * @param subj the subj
     * @return the integer
     */
    public Integer insertSubjectIntoDB(String subj){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertSubject(subj, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert subject into DB");
        }
        return null;
    }
}
