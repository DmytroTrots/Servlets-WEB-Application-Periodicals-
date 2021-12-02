package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.rerository.SubjectDao;
import com.trots.periodacals.rerository.UserDao;
import com.trots.periodacals.rerository.mysql.MySQLDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class SubjectService {
    private static final Logger log = LogManager.getLogger(SubjectService.class);

    private static SubjectService instance;

    public static synchronized SubjectService getInstance() {
        if (instance == null) {
            instance = new SubjectService();
        }
        return instance;
    }

    SubjectDao repository = new MySQLDaoFactory().getSubjectDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Map<String, Integer> findAllSubjectsFromDB(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findAllSubjects(con);
        } catch (SQLException throwables) {
            log.error("Cannot get all subjects");
        }
        return Collections.emptyMap();
    }

    public Integer insertSubjectIntoDB(String subj){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertSubject(subj, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert subject into DB");
        }
        return null;
    }
}
