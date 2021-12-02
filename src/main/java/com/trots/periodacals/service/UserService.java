package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.rerository.UserDao;
import com.trots.periodacals.rerository.mysql.MySQLDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private static UserService instance;

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    UserDao repository = new MySQLDaoFactory().getUserDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public String authenticateUser(User user) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.loginCheck(con, user);
        } catch (SQLException e) {
            log.error("Invalid credentials");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Cant validate password");
        }
        return "Invalid user credentials";
    }

    public List<User> findAllUsers() {
        try (Connection con = connectionPool.getConnection()) {
            return repository.findAll(con);
        } catch (SQLException throwables) {
            log.error("Cannot find any user");
        }
        return Collections.emptyList();
    }


    public Integer addUser(User user) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.userRegistration(user, con);
        } catch (SQLException throwables) {
            log.error("Cannot register user, admin page");
        }
        return null;
    }

    public boolean updateFieldBalanceAfterTopUp(int id, Double balance) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.updateBalanceTopUp(id, balance, con);
        } catch (SQLException throwables) {
            log.error("Cannot top up balance");
        }
        return false;
    }

    public User getSingleUserById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.findSingleUserById(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get user by ID");
        }
        return null;
    }

    public boolean updateBanStatusOfUser(String status, Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.setBanStatus(status, id, con);
        } catch (SQLException throwables) {
            log.error("Cannot ban user");
        }
        return false;
    }

    public boolean deleteUserFromAdminPage(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.deleteUserAdmin(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot delete user, admin page");
        }
        return false;
    }
}
