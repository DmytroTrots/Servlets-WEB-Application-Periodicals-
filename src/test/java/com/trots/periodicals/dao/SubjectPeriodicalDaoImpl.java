package com.trots.periodicals.dao;

import com.trots.periodacals.dbconnection.DBManager;
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
        boolean result = false;
        try{
            connection.setAutoCommit(false);
            result = DBManager.getInstance().insertSubjIdAndPeriodicalId(22, 53, connection);

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
        List<String> list = null;
        try{
            list = DBManager.getInstance().getSubjectOfPeriodById(17, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

}
