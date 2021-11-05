package com.trots.periodacals.dao;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;

import java.sql.*;

public class UserDao {

    Connection con = ConnectionPool.getInstance().getConnection();

    public boolean addUser(User user, String role) throws SQLException {
        boolean result = false;
        int idOfRole = 0;
        try(PreparedStatement ps = con.prepareStatement("SELECT `role`.`id` FROM role WHERE `name` = ?");
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `dbperiodicals`.`user` " +
                "(`username`, `email`, `password`, `role_id`, `telephone`, `name`, `surname`)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);"
            )){
            ps.setString(1, role);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                idOfRole = resultSet.getInt("id");
            }
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, idOfRole);
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            preparedStatement.executeUpdate();
            result = true;

        }
        return result;
    }

}




