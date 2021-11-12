package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;
import com.trots.periodacals.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoImpl {

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
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }

    public List<User> findAllUsers() {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.findAll(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public boolean registerUser(User user) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.registrationMethod(user, con);
        } catch (SQLException e) {
            Logger.getLogger(e.getMessage());
        }
        return false;
    }

    public boolean addUser(User user) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.registrationByAdmin(user, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateFieldBalanceAfterTopUp(int id, Double balance) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.updateBalanceTopUp(id, balance, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Double getBalanceOfUserById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.findBalanceOfUserById(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getSingleUserById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.findSingleUserById(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean updateBanStatusOfUser(String status, Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.setBanStatus(status, id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteUserFromAdminPage(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.deleteUserAdmin(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
