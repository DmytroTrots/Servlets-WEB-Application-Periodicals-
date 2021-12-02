package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.rerository.PublisherDao;
import com.trots.periodacals.rerository.mysql.MySQLDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class PublisherService {
    private static final Logger log = LogManager.getLogger(PublisherService.class);

    private static PublisherService instance;

    public static synchronized PublisherService getInstance() {
        if (instance == null) {
            instance = new PublisherService();
        }
        return instance;
    }

    PublisherDao repository = new MySQLDaoFactory().getPublisherDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Map<String, Integer> findAllPublishersWithoutTelephone(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findAllPublishers(con);
        } catch (SQLException e) {
            log.error("Cannot find all publisher without telephone");
        }
        return Collections.emptyMap();
    }

    public Integer insertPublisherIntoDB(String publisher, String telephone){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertPublisher(publisher, telephone, con);
        } catch (SQLException e) {
            log.error("Cannot insert publisher into DB");
        }
        return null;
    }
}
