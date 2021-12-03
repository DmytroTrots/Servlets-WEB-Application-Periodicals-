package com.trots.periodicals.dao;

import com.trots.periodacals.rerository.mysql.SubjectPeriodicalsDaoImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SubjectPeriodicalDaoImpl {

    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String url = "jdbc:mysql://localhost:3306/dbperiodicals";
        connection = DriverManager.getConnection(url, "root", "password");
    }

    @Test
    public void insertSubjIdAndPeriodicalIdTest(){
        SubjectPeriodicalsDaoImpl subjectPeriodicalsDaoImpl = new SubjectPeriodicalsDaoImpl();
        boolean result = false;
        try{
            connection.setAutoCommit(false);
            result = subjectPeriodicalsDaoImpl.insertSubjIdAndPeriodicalId(27, 99, connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(result);
    }

    @Test
    public void getSubjectOfPeriodByIdTest(){
        SubjectPeriodicalsDaoImpl subjectPeriodicalsDaoImpl = new SubjectPeriodicalsDaoImpl();
        List<String> list = null;
        try{
            list = subjectPeriodicalsDaoImpl.getSubjectOfPeriodById(17, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

}
