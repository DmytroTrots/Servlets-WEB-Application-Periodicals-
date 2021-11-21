package com.trots.periodicals.dao;

import com.trots.periodacals.dbconnection.DBManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class PublisherDaoTest {

    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String url = "jdbc:mysql://localhost:3306/dbperiodicals";
        connection = DriverManager.getConnection(url, "root", "password");
    }

    @Test
    public void findAllPublisherTest(){
        Map<String, Integer> publisherMap = null;
        try{
            publisherMap = DBManager.getInstance().findAllPublishers(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(publisherMap);
    }

    @Test
    public void insertPublisherIntoDBTest(){
        Integer result = null;
        try{
            connection.setAutoCommit(false);
            result = DBManager.getInstance().insertPublisher("publisher7", "38099999999", connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(result);
    }

}
