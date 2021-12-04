package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao {
    /**
     * Login check string.
     *
     * @param connection the connection
     * @param user       the user
     * @return the string
     * @throws SQLException             the sql exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws InvalidKeySpecException  the invalid key spec exception
     */
    String loginCheck(Connection connection, User user) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    /**
     * Find all list.
     *
     * @param con the con
     * @return the list
     * @throws SQLException the sql exception
     */
///method for finding all users of DataBase
    List<User> findAll(Connection con) throws SQLException;

    /**
     * User registration integer.
     *
     * @param user the user
     * @param con  the con
     * @return the integer
     * @throws SQLException the sql exception
     */
///method for registering user by Admin(connect with previous method)
    Integer userRegistration(User user, Connection con) throws SQLException;

    /**
     * Update balance top up boolean.
     *
     * @param id      the id
     * @param balance the balance
     * @param con     the con
     * @return the boolean
     * @throws SQLException the sql exception
     */
///method for balance updating
    boolean updateBalanceTopUp(int id, Double balance, Connection con) throws SQLException;

    /**
     * Find single user by id user.
     *
     * @param id  the id
     * @param con the con
     * @return the user
     * @throws SQLException the sql exception
     */
///method for finding one user by id
    User findSingleUserById(Integer id, Connection con) throws SQLException;

    /**
     * Sets ban status.
     *
     * @param status the status
     * @param id     the id
     * @param con    the con
     * @return the ban status
     * @throws SQLException the sql exception
     */
///method for ban user
    boolean setBanStatus(String status, Integer id, Connection con) throws SQLException;

    /**
     * Delete user admin boolean.
     *
     * @param id  the id
     * @param con the con
     * @return the boolean
     * @throws SQLException the sql exception
     */
///method for deleting user
    boolean deleteUserAdmin(Integer id, Connection con) throws SQLException;

    /**
     * Find user by username user.
     *
     * @param username the username
     * @param con      the con
     * @return the user
     * @throws SQLException the sql exception
     */
    User findUserByUsername(String username, Connection con) throws SQLException;

    /**
     * Find user by mail user.
     *
     * @param mail the mail
     * @param con  the con
     * @return the user
     * @throws SQLException the sql exception
     */
    User findUserByMail(String mail, Connection con) throws SQLException;

    /**
     * Find user by telephone user.
     *
     * @param telephone the telephone
     * @param con       the con
     * @return the user
     * @throws SQLException the sql exception
     */
    User findUserByTelephone(String telephone, Connection con) throws SQLException;
}
