package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.rerository.PublisherDao;
import com.trots.periodacals.rerository.mysql.DaoImplFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

/**
 * The type Publisher service.
 */
public class PublisherService {
    private static final Logger log = LogManager.getLogger(PublisherService.class);

    private static PublisherService instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized PublisherService getInstance() {
        if (instance == null) {
            instance = new PublisherService();
        }
        return instance;
    }

    /**
     * The Repository.
     */
    PublisherDao repository = new DaoImplFactory().getPublisherDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Find all publishers without telephone map.
     *
     * @return the map
     */
    public Map<String, Integer> findAllPublishersWithoutTelephone(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findAllPublishers(con);
        } catch (SQLException e) {
            log.error("Cannot find all publisher without telephone");
        }
        return Collections.emptyMap();
    }

    /**
     * Insert publisher into db integer.
     *
     * @param publisher the publisher
     * @param telephone the telephone
     * @return the integer
     */
    public Integer insertPublisherIntoDB(String publisher, String telephone){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertPublisher(publisher, telephone, con);
        } catch (SQLException e) {
            log.error("Cannot insert publisher into DB");
        }
        return null;
    }
}
