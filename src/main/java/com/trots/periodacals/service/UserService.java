package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.rerository.UserDao;
import com.trots.periodacals.rerository.mysql.DaoImplFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * The type User service.
 */
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private static UserService instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    /**
     * The Repository.
     */
    UserDao repository = new DaoImplFactory().getUserDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Authenticate user string.
     *
     * @param user the user
     * @return the string
     */
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

    /**
     * Find all users list.
     *
     * @return the list
     */
    public List<User> findAllUsers() {
        try (Connection con = connectionPool.getConnection()) {
            return repository.findAll(con);
        } catch (SQLException throwables) {
            log.error("Cannot find any user");
        }
        return Collections.emptyList();
    }


    /**
     * Add user integer.
     *
     * @param user the user
     * @return the integer
     */
    public Integer addUser(User user) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.userRegistration(user, con);
        } catch (SQLException throwables) {
            log.error("Cannot register user");
        }
        return null;
    }

    /**
     * Update field balance after top up boolean.
     *
     * @param id      the id
     * @param balance the balance
     * @return the boolean
     */
    public boolean updateFieldBalanceAfterTopUp(int id, Double balance) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.updateBalanceTopUp(id, balance, con);
        } catch (SQLException throwables) {
            log.error("Cannot top up balance");
        }
        return false;
    }

    /**
     * Gets single user by id.
     *
     * @param id the id
     * @return the single user by id
     */
    public User getSingleUserById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.findSingleUserById(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get user by ID");
        }
        return null;
    }

    /**
     * Update ban status of user boolean.
     *
     * @param status the status
     * @param id     the id
     * @return the boolean
     */
    public boolean updateBanStatusOfUser(String status, Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.setBanStatus(status, id, con);
        } catch (SQLException throwables) {
            log.error("Cannot ban user");
        }
        return false;
    }

    /**
     * Delete user from admin page boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteUserFromAdminPage(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.deleteUserAdmin(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot delete user, admin page");
        }
        return false;
    }

    public User getSingleUserByUsername(String username) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.findUserByUsername(username, con);
        } catch (SQLException throwables) {
            log.error("Cannot get user by username");
        }
        return null;
    }

    public User getSingleUserByTelephone(String telephone) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.findUserByTelephone(telephone, con);
        } catch (SQLException throwables) {
            log.error("Cannot get user by telephone");
        }
        return null;
    }

    public User getSingleUserByEmail(String email) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.findUserByMail(email, con);
        } catch (SQLException throwables) {
            log.error("Cannot get user by email");
        }
        return null;
    }
}
