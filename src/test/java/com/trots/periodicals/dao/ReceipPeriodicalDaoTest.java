package com.trots.periodicals.dao;

import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.mysql.ReceiptHasPeriodicalsDaoImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReceipPeriodicalDaoTest {

    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String url = "jdbc:mysql://localhost:3306/dbperiodicals";
        connection = DriverManager.getConnection(url, "root", "password");
    }

    @Test
    public void insertRecordIntoReceiptHasPeriodicalsTest(){
        ReceiptHasPeriodicalsDaoImpl receiptHasPeriodicalsDaoImpl = new ReceiptHasPeriodicalsDaoImpl();
        boolean result = false;
        try{
            Receipt receipt = new Receipt();
            receipt.setName("testName");
            receipt.setSurname("testSurname");
            receipt.setEmail("testEmail@gmail.com");
            receipt.setTelephoneNumber("380999999999");
            receipt.setAddress("testAddress");
            receipt.setUserId(82);
            receipt.setPeriodicalSellId(93);
            receipt.setStatusId(1);
            connection.setAutoCommit(false);
            result = receiptHasPeriodicalsDaoImpl.insertRecordIntoReceiptHasPeriodicals(receipt, 102, connection);
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
    public void getAllOrdersForProccessTest(){
        ReceiptHasPeriodicalsDaoImpl receiptHasPeriodicalsDaoImpl = new ReceiptHasPeriodicalsDaoImpl();
        List<Receipt> list = null;
        try{
            list = receiptHasPeriodicalsDaoImpl.getAllOrdersForProccess(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

}
