package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class SubjectDaoImpl {

    private static final Logger log = LogManager.getLogger(SubjectDaoImpl.class);

    private static SubjectDaoImpl instance;

    public static synchronized SubjectDaoImpl getInstance() {
        if (instance == null) {
            instance = new SubjectDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Map<String, Integer> findAllSubjectsFromDB(){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.findAllSubjects(con);
        } catch (SQLException throwables) {
            log.error("Cannot get all subjects");
        }
        return Collections.emptyMap();
    }

    public Integer insertSubjectIntoDB(String subj){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.insertSubject(subj, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert subject into DB");
        }
        return null;
    }


}
