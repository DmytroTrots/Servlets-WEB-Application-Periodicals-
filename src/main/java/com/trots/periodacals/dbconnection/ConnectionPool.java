package com.trots.periodacals.dbconnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool implements ConnectionBuilder{

    private DataSource dataSource;

    private static ConnectionPool instance = null;

    public ConnectionPool() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource)context.lookup("java:comp/env/jdbc/connectionPool");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public synchronized ConnectionPool getInstance(){
        if(instance==null){
            instance = new ConnectionPool();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
