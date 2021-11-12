package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class PublisherDaoImpl {

    private static PublisherDaoImpl instance;

    public static synchronized PublisherDaoImpl getInstance() {
        if (instance == null) {
            instance = new PublisherDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Map<String, Integer> findAllPublishersWithoutTelephone(){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.findAllPublishers(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyMap();
    }

    public Integer insertPublisherIntoDB(String publisher, String telephone){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.insertPublisher(publisher, telephone, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
