package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.entity.User;
import com.trots.periodacals.rerository.UserDao;
import com.trots.periodacals.util.PBKDF2PasswordHashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDao implements UserDao, SQLQuery {

    @Override
    public String loginCheck(Connection connection, User user) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String username = user.getUsername();
        String password = user.getPassword();

        String passwordDB;
        String roleDB;
        String userNameDB;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SELECT_CREDENTIALS_LOGIN)) {
            while (resultSet.next()) {
                userNameDB = resultSet.getString("username");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role");

                if (username.equals(userNameDB) && PBKDF2PasswordHashing.validatePassword(password, passwordDB) && roleDB.equals("admin"))
                    return "admin";
                else if (username.equals(userNameDB) && PBKDF2PasswordHashing.validatePassword(password, passwordDB) && roleDB.equals("customer"))
                    return "customer";
            }
            return "invalid user Credential";
        }
    }

    @Override
    public List<User> findAll(Connection con) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {
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
        }
        return users;
    }

    @Override
    public Integer userRegistration(User user, Connection con) throws SQLException {
        Integer userId = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_BY_ADMIN, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys()){
                if (resultSet.next()){
                    userId = resultSet.getInt(1);
                }
            }
            return userId;
        }
    }

    @Override
    public boolean updateBalanceTopUp(int id, Double balance, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_BALANCE_AFTER_TOP_UP)) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public User findSingleUserById(Integer id, Connection con) throws SQLException {
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_SINGLE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                    user.setTelephone(resultSet.getString("telephone"));
                    user.setAddress(resultSet.getString("address"));
                    user.setBalance(resultSet.getDouble("balance"));
                    user.setBanStatus(resultSet.getString("ban_status"));
                }
            }
        }
        return user;
    }

    @Override
    public boolean setBanStatus(String status, Integer id, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(SET_BAN_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean deleteUserAdmin(Integer id, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_USER_FROM_ADMIN_PAGE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }
}
