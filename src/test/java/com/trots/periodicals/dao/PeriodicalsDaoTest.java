package com.trots.periodicals.dao;

import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.rerository.mysql.PeriodicalsDaoImpl;
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
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        List<Periodical> periodicals = null;
        try {
            periodicals = periodicalsDaoImpl.getAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(periodicals);
    }

    @Test
    public void insertPeriodicalTest(){
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        Integer periodicalId = null;
        try{
            connection.setAutoCommit(false);
            periodicalId = periodicalsDaoImpl.insertPeriodical(periodical, connection);
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
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        Double priceOfPeriodical = null;
        try{
            connection.setAutoCommit(false);
            Integer periodicalId = periodicalsDaoImpl.insertPeriodical(periodical, connection);
            priceOfPeriodical = periodicalsDaoImpl.getPriceOfOnePeriodical(periodicalId, connection);
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
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = periodicalsDaoImpl.deletePeriodicalAdmin(43, connection);
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
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        Periodical periodical1 = new Periodical();
        try {
            connection.setAutoCommit(false);
            Integer periodicalId = periodicalsDaoImpl.insertPeriodical(periodical, connection);
            periodical1 = periodicalsDaoImpl.getOnePeriod(periodicalId, connection);
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

    @Test
    public void updatePeriodicalByAdmin() {
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            Integer periodicalId = periodicalsDaoImpl.insertPeriodical(periodical, connection);
            result = periodicalsDaoImpl.updatePeriodical(periodical, periodicalId, "java.png", periodical.getImage(), connection);
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
    public void getRecordsForPaginSortSearchTest(){
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        List<Periodical> list = null;
        try {
            list = periodicalsDaoImpl.getRecords("SELECT `periodical`.`sell_id`, periodical.rating, `periodical`.`title`, `periodical`.`price_per_month`,`periodical`.`images`, `publisher`.`name` FROM periodical_has_subject JOIN periodical ON periodical_has_subject.periodical_id = periodical.sell_id JOIN publisher ON periodical.publisher_id = publisher.id JOIN `subject` ON periodical_has_subject.subject_id = `subject`.id group by periodical.sell_id", connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

    @Test
    public void getNumberOfRowsOfPeriodicalTableTest(){
        PeriodicalsDaoImpl periodicalsDaoImpl = new PeriodicalsDaoImpl();
        int result = 0;
        try {
            result = periodicalsDaoImpl.getNumberOfRows(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertNotEquals(0, result);
    }

}
