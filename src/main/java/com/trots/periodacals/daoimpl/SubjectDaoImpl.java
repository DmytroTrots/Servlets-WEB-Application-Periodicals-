package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class SubjectDaoImpl {

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
            throwables.printStackTrace();
        }
        return Collections.emptyMap();
    }

    public Integer insertSubjectIntoDB(String subj){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.insertSubject(subj, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
