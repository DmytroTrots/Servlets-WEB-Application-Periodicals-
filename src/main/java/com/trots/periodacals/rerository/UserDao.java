package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    String loginCheck(Connection connection, User user) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    ///method for finding all users of DataBase
    List<User> findAll(Connection con) throws SQLException;

    ///method for registering user by Admin(connect with previous method)
    Integer userRegistration(User user, Connection con) throws SQLException;

    ///method for balance updating
    boolean updateBalanceTopUp(int id, Double balance, Connection con) throws SQLException;

    ///method for finding one user by id
    User findSingleUserById(Integer id, Connection con) throws SQLException;

    ///method for ban user
    boolean setBanStatus(String status, Integer id, Connection con) throws SQLException;

    ///method for deleting user
    boolean deleteUserAdmin(Integer id, Connection con) throws SQLException;
}
