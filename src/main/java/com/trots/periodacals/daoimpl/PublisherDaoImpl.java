package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class PublisherDaoImpl {

    private static final Logger log = LogManager.getLogger(PublisherDaoImpl.class);

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
        } catch (SQLException e) {
            log.error("Cannot find all publisher without telephone");
        }
        return Collections.emptyMap();
    }

    public Integer insertPublisherIntoDB(String publisher, String telephone){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.insertPublisher(publisher, telephone, con);
        } catch (SQLException e) {
            log.error("Cannot insert publisher into DB");
        }
        return null;
    }
}
