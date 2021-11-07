package com.trots.periodacals.dao;

import com.trots.periodacals.dbconnection.ConnectionBuilder;
import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.repo.UserRepo;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class UserDao {

    private ConnectionBuilder connectionBuilder;

    private UserRepo userRepo;

    public String authenticateUser(User user) {
        try (Connection con = getConnection()) {
            return userRepo.loginCheck(user, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }

    public boolean addUser(User user) {
        try (Connection con = getConnection()) {
            return userRepo.registrationByAdmin(user, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(User user) {
        try (Connection con = getConnection()) {
            return userRepo.registrationMethod(user, con);
        } catch (SQLException e) {
            Logger.getLogger(e.getMessage());
        }
        return false;
    }

    public List<User> findAllUsers() {
        try (Connection con = getConnection()) {
            return userRepo.findAll(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    private Connection getConnection() throws SQLException {

        return connectionBuilder.getConnection();
    }

    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }
}





