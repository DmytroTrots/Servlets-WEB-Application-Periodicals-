package com.trots.periodicals.dao;

import com.trots.periodacals.rerository.mysql.PublisherDaoImpl;
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
        PublisherDaoImpl publisherDaoImpl = new PublisherDaoImpl();
        Map<String, Integer> publisherMap = null;
        try{
            publisherMap = publisherDaoImpl.findAllPublishers(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(publisherMap);
    }

    @Test
    public void insertPublisherIntoDBTest(){
        PublisherDaoImpl publisherDaoImpl = new PublisherDaoImpl();
        Integer result = null;
        try{
            connection.setAutoCommit(false);
            result = publisherDaoImpl.insertPublisher("publisher7", "38099999999", connection);
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
