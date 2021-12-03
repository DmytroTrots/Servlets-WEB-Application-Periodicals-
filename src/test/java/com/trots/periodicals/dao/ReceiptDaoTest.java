package com.trots.periodicals.dao;

import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.mysql.ReceiptDaoImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ReceiptDaoTest {

    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String url = "jdbc:mysql://localhost:3306/dbperiodicals";
        connection = DriverManager.getConnection(url, "root", "password");
    }

    @Test
    public void insertOrderTest(){
        ReceiptDaoImpl receiptDaoImpl = new ReceiptDaoImpl();
        Integer result = null;
        try{
            Receipt receipt = new Receipt();
            receipt.setName("testName");
            receipt.setSurname("testSurname");
            receipt.setEmail("testEmail@gmail.com");
            receipt.setTelephoneNumber("380999999999");
            receipt.setAddress("testAddress");
            receipt.setUserId(82);
            receipt.setStatusId(1);
            connection.setAutoCommit(false);
            result = receiptDaoImpl.insertOrder(receipt, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } Assert.assertNotNull(result);
    }

    @Test
    public void findOrdersOfOneOrdersTest(){
        ReceiptDaoImpl receiptDaoImpl = new ReceiptDaoImpl();
        List<Receipt> list = null;
        try{
            list = receiptDaoImpl.findOrdersOfOneUser(46, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

    @Test
    public void findAllAcceptedOrdersTest(){
        ReceiptDaoImpl receiptDaoImpl = new ReceiptDaoImpl();
        List<Receipt> list = null;
        try{
            list = receiptDaoImpl.findAllAcceptedOrdersOfUser(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Assert.assertNotNull(list);
    }

}
