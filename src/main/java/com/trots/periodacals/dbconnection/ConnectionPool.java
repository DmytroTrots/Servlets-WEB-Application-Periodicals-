package com.trots.periodacals.dbconnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if(instance==null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection(){
        Context context;
        Connection con = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/connectionPool");
            con = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
