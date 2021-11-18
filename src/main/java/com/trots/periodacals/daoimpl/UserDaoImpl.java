package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.dbconnection.DBManager;
import com.trots.periodacals.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl {

    private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

    private static UserDaoImpl instance;

    public static synchronized UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public String authenticateUser(User user) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.loginCheck(con, user);
        } catch (SQLException e) {
            log.error("Invalid credentials");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Cant validate password");
        }
        return "Invalid user credentials";
    }

    public List<User> findAllUsers() {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.findAll(con);
        } catch (SQLException throwables) {
            log.error("Cannot find any user");
        }
        return Collections.emptyList();
    }


    public boolean addUser(User user) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.userRegistration(user, con);
        } catch (SQLException throwables) {
            log.error("Cannot register user, admin page");
        }
        return false;
    }

    public boolean updateFieldBalanceAfterTopUp(int id, Double balance) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.updateBalanceTopUp(id, balance, con);
        } catch (SQLException throwables) {
            log.error("Cannot top up balance");
        }
        return false;
    }

    public User getSingleUserById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.findSingleUserById(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get user by ID");
        }
        return null;
    }

    public boolean updateBanStatusOfUser(String status, Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.setBanStatus(status, id, con);
        } catch (SQLException throwables) {
            log.error("Cannot ban user");
        }
        return false;
    }

    public boolean deleteUserFromAdminPage(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.deleteUserAdmin(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot delete user, admin page");
        }
        return false;
    }

}
