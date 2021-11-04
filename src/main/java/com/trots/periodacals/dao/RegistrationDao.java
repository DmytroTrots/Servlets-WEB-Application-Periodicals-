package com.trots.periodacals.dao;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class RegistrationDao {

    Connection con = ConnectionPool.getInstance().getConnection();

    public int registerUser(User user){
        int result = 0;
        try(PreparedStatement preparedStatement =
                    con.prepareStatement("INSERT INTO `dbperiodicals`.`user` " +
                            "(`username`, `email`, `password`, `role_id`, `telephone`, `name`, `surname`) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?);")){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, 2);
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            Logger.getLogger(e.getMessage());
        }
        return result;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user")){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setCreate_time(resultSet.getDate(5));
                user.setRoleId(resultSet.getInt(6));
                user.setTelephone(resultSet.getString(7));
                user.setName(resultSet.getString(8));
                user.setSurname(resultSet.getString(9));
                user.setBanStatus(resultSet.getString(10));
                users.add(user);
            }
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return Collections.emptyList();
        }
        return users;
    }


}
