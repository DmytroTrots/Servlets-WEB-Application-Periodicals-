package com.trots.periodicals.dao;

import com.trots.periodacals.dbconnection.DBManager;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class PeriodicalsDaoTest {

    private static Connection connection;

    private static Periodical periodical;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String url = "jdbc:mysql://localhost:3306/dbperiodicals";
        connection = DriverManager.getConnection(url, "root", "password");
        periodical = new Periodical();
        periodical.setRating(2);
        periodical.setPeriodicityPerYear(2);
        periodical.setNumberOfPages(2);
        periodical.setImage("image");
        periodical.setPercentageOfAdvertising(4);
        periodical.setDescription("description");
        periodical.setTitle("title");
        periodical.setPublisherId(12);
        periodical.setPricePerMonth(16.1);
    }

    @Test
    public void findAllPeriodicalsTest() {
        List<Periodical> periodicals = null;
        try {
            periodicals = DBManager.getInstance().getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(periodicals);
    }

    @Test
    public void insertPeriodicalTest(){
        Integer periodicalId = null;
        try{
            connection.setAutoCommit(false);
            periodicalId = DBManager.getInstance().insertPeriodical(periodical, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(periodicalId);
    }

    @Test
    public void getPriceOfOnePeriodical(){
        Double priceOfPeriodical = null;
        try{
            connection.setAutoCommit(false);
            Integer periodicalId = DBManager.getInstance().insertPeriodical(periodical, connection);
            priceOfPeriodical = DBManager.getInstance().getPriceOfOnePeriodical(periodicalId, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(periodical.getPricePerMonth(), priceOfPeriodical);
    }

    @Test
    public void deletePeriodicalByAdmin() {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = DBManager.getInstance().deletePeriodicalAdmin(43, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertTrue(result);
    }

    @Test
    public void getPeriodicalById() {
        Periodical periodical1 = new Periodical();
        try {
            connection.setAutoCommit(false);
            Integer periodicalId = DBManager.getInstance().insertPeriodical(periodical, connection);
            periodical1 = DBManager.getInstance().getOnePeriod(periodicalId, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(periodical.getPeriodicityPerYear(), periodical1.getPeriodicityPerYear());
        Assert.assertEquals(periodical.getNumberOfPages(), periodical1.getNumberOfPages());
        Assert.assertEquals(periodical.getImage(), periodical1.getImage());
        Assert.assertEquals(periodical.getPercentageOfAdvertising(), periodical1.getPercentageOfAdvertising());
        Assert.assertEquals(periodical.getDescription(), periodical1.getDescription());
        Assert.assertEquals(periodical.getTitle(), periodical1.getTitle());
        Assert.assertEquals(periodical.getPricePerMonth(), periodical1.getPricePerMonth());
    }
}
