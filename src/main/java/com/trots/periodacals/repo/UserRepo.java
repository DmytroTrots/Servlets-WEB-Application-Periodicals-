package com.trots.periodacals.repo;

import com.trots.periodacals.dbconnection.SQLQuery;
import com.trots.periodacals.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    public List<User> findAll(Connection con) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_USERS)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setCreate_time(resultSet.getDate(5));
                user.setTelephone(resultSet.getString(6));
                user.setName(resultSet.getString(7));
                user.setSurname(resultSet.getString(8));
                user.setBanStatus(resultSet.getString(9));
                user.setBalance(resultSet.getBigDecimal(10));
                user.setRole(resultSet.getString(11));
                users.add(user);
            }
            return users;
        }
    }

    public boolean registrationMethod(User user, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(SQLQuery.INSERT_USER_REGISTRATION)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, "customer");
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            preparedStatement.executeUpdate();
        }
        return true;
    }

    public boolean registrationByAdmin(User user, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(SQLQuery.INSERT_USER_BY_ADMIN)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            preparedStatement.executeUpdate();
            return true;
        }
    }

    public String loginCheck(User user, Connection con) throws SQLException {
        String username = user.getUsername();
        String password = user.getPassword();

        String passwordDB;
        String roleDB;
        String userNameDB;
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(SQLQuery.SELECT_CREDENTIALS_LOGIN)) {
            while (resultSet.next()) {
                userNameDB = resultSet.getString("username");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role");

                if (username.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("admin"))
                    return "admin";
                else if (username.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("manager"))
                    return "manager";
                else if (username.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("customer"))
                    return "customer";
            }
            return "Invalid user credentials";
        }
    }
}
