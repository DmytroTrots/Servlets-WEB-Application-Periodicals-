package com.trots.periodicals.dao;

import com.trots.periodacals.entity.User;
import com.trots.periodacals.rerository.mysql.UserDaoImpl;
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
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        List<User> users = null;
        try {
            users = userDaoImpl.findAll(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(users);
    }

    @Test
    public void registerUserTest() {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
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
            userId = userDaoImpl.userRegistration(user, connection);
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
        UserDaoImpl userDaoImpl = new UserDaoImpl();
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
            userId = userDaoImpl.userRegistration(user, connection);
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
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = new User();
        try {
            user = userDaoImpl.findSingleUserById(82, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("380999999999", user.getTelephone());
        Assert.assertEquals("adminName", user.getName());
        Assert.assertEquals("adminSurname", user.getSurname());
        Assert.assertEquals("adminAddress", user.getAddress());
        Assert.assertEquals("admin@gmail.com", user.getEmail());
    }

    @Test
    public void topUpBalanceOfUserTest() {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = userDaoImpl.updateBalanceTopUp(43, 1000.0, connection);
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
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = userDaoImpl.setBanStatus("banned", 43, connection);
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
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        boolean result = false;
        try {
            connection.setAutoCommit(false);
            result = userDaoImpl.deleteUserAdmin(43, connection);
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
        UserDaoImpl userDaoImpl = new UserDaoImpl();
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
            Integer userId = userDaoImpl.userRegistration(user, connection);
            user.setPassword("testPassword");
            check = userDaoImpl.loginCheck(connection, user);
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

