package com.trots.periodacals.dbconnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool instance;

    private DataSource dataSource;

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public ConnectionPool() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/connectionPool");
        } catch (NamingException ex) {
            throw new IllegalStateException("Cannot obtain a data source", ex);
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("Can't obtain a connection", e);
        }
        return con;
    }
}
