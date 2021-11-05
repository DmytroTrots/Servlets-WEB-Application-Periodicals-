package com.trots.periodacals.dao;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {

    Connection con = ConnectionPool.getInstance().getConnection();

    public String authenticateUser(User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();

        String userNameDB = "";
        String passwordDB = "";
        String roleDB = "";

        try(Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT `username`, `password`, `role`.`name` " +
                    "FROM `user`, `role` " +
                    "WHERE `role`.`id` = `role_id`;"))
        {
            while(resultSet.next())
            {
                userNameDB = resultSet.getString("username");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("name");

                if(username.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("admin"))
                    return "admin";
                else if(username.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("manager"))
                    return "manager";
                else if(username.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("customer"))
                    return "customer";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }
}
