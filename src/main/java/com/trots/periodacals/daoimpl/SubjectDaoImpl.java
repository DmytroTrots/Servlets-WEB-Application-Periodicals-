package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dao.SubjectDao;
import com.trots.periodacals.dbconnection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SubjectDaoImpl {
    SubjectDao subjectDao = new SubjectDao();

    public Map<String, Integer> findAllSubjectsFromDB(){
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            return subjectDao.findAllSubjects(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyMap();
    }

    public Integer insertSubjectIntoDB(String subj){
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            return subjectDao.insertSubject(subj, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
