package com.trots.periodicals.dao;

import com.trots.periodacals.rerository.mysql.MysSQLPublisherDao;
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
        MysSQLPublisherDao mysSQLPublisherDao = new MysSQLPublisherDao();
        Map<String, Integer> publisherMap = null;
        try{
            publisherMap = mysSQLPublisherDao.findAllPublishers(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(publisherMap);
    }

    @Test
    public void insertPublisherIntoDBTest(){
        MysSQLPublisherDao mysSQLPublisherDao = new MysSQLPublisherDao();
        Integer result = null;
        try{
            connection.setAutoCommit(false);
            result = mysSQLPublisherDao.insertPublisher("publisher7", "38099999999", connection);
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
