package com.trots.periodacals.dao;

import com.trots.periodacals.dbconnection.SQLQuery;
import com.trots.periodacals.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

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
                user.setBalance(resultSet.getDouble(10));
                user.setRole(resultSet.getString(11));
                user.setAddress(resultSet.getString(12));
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
            preparedStatement.setString(8, user.getAddress());
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
            preparedStatement.setString(8, user.getAddress());
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
            return "invalid user Credential";
        }
    }

    public boolean updateBalanceTopUp(int id, Double balance, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE `dbperiodicals`.`user` SET `balance` = ? WHERE (`id` = ?)")) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        }
        return true;
    }

    public Double findBalanceOfUserById(Integer id, Connection con) throws SQLException {
        Double balance = null;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT `balance` FROM user WHERE (`id` = ?)")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return balance;
    }

    public User findSingleUserById(Integer id, Connection con) throws SQLException {
        User user = null;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM user WHERE (`id` = ?)")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setTelephone(resultSet.getString("telephone"));
                user.setAddress(resultSet.getString("address"));
                user.setBanStatus(resultSet.getString("ban_status"));
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return user;
    }

    public boolean setBanStatus(String status, Integer id, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE `dbperiodicals`.`user` " +
                "SET `ban_status` = ? WHERE (`id` = ?)")) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    public boolean deleteUserAdmin(Integer id, Connection con) throws SQLException {
        try(PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM `dbperiodicals`.`user` WHERE (`id` = ?)")){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }
}






