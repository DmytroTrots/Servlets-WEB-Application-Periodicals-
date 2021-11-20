package com.trots.periodicals.dao;

import com.trots.periodacals.dbconnection.DBManager;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.util.PBKDF2PasswordHashing;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.List;

public class UsersDaoTest {

    private static Connection connection;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String url = "jdbc:mysql://localhost:3306/dbperiodicals";
        connection = DriverManager.getConnection(url, "root", "password");
    }

    @Test
    public void findAllUsersTest() {
        List<User> users = null;
        try {
            users = DBManager.getInstance().findAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(users);
    }

    @Test
    public void registerUserTest() {
        Integer userId = null;
        User user = new User();
        user.setName("testName");
        user.setBalance(16.1);
        user.setSurname("testSurname");
        user.setPassword("testPassword");
        user.setEmail("test@gmail.com");
        user.setAddress("testAddress");
        user.setTelephone("380999900099");
        user.setUsername("testUsername");
        user.setRole("customer");
        try {
            connection.setAutoCommit(false);
            userId = DBManager.getInstance().userRegistration(user, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(userId);
    }

    @Test(expected = AssertionError.class)
    public void registerUserExceptionTest() {
        Integer userId = null;
        User user = new User();
        user.setName("testName");
        user.setBalance(16.1);
        user.setSurname("testSurname");
        user.setPassword("testPassword");
        user.setEmail("test@gmail.com");
        user.setAddress("testAddress");
        user.setTelephone("380999900099");
        user.setUsername("admin");
        user.setRole("customer");
        try {
            connection.setAutoCommit(false);
            userId = DBManager.getInstance().userRegistration(user, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertNotNull(userId);
    }

    @Test
    public void findUserById() {
        User user = new User();
        try {
            user = DBManager.getInstance().findSingleUserById(43, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("380990990990", user.getTelephone());
        Assert.assertEquals("adminName", user.getName());
        Assert.assertEquals("adminSurname", user.getSurname());
        Assert.assertEquals("adminAddress", user.getAddress());
        Assert.assertEquals("admin@gmail.com", user.getEmail());
    }

    @Test
    public void topUpBalanceOfUserTest() {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = DBManager.getInstance().updateBalanceTopUp(43, 1000.0, connection);
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
    public void setBanStatusTest() {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = DBManager.getInstance().setBanStatus("banned", 43, connection);
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
    public void deleteUserByAdmin() {
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = DBManager.getInstance().deleteUserAdmin(43, connection);
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
    public void loginCheckTest() {
        User user = new User();
        user.setName("testName");
        user.setBalance(16.1);
        user.setSurname("testSurname");
        try {
            user.setPassword(PBKDF2PasswordHashing.generateStorngPasswordHash("testPassword"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        user.setEmail("test@gmail.com");
        user.setAddress("testAddress");
        user.setTelephone("380999900099");
        user.setUsername("testUsername");
        user.setRole("admin");
        String check = null;
        try {
            connection.setAutoCommit(false);
            Integer userId = DBManager.getInstance().userRegistration(user, connection);
            user.setPassword("testPassword");
            check = DBManager.getInstance().loginCheck(connection, user);
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals("admin", check);
    }
}

