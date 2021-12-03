package com.trots.periodacals.rerository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * The interface Publisher dao.
 */
public interface PublisherDao {
    /**
     * Find all publishers map.
     *
     * @param con the con
     * @return the map
     * @throws SQLException the sql exception
     */
    Map<String, Integer> findAllPublishers(Connection con) throws SQLException;

    /**
     * Insert publisher integer.
     *
     * @param publisher the publisher
     * @param telephone the telephone
     * @param con       the con
     * @return the integer
     * @throws SQLException the sql exception
     */
    Integer insertPublisher(String publisher, String telephone, Connection con) throws SQLException;
}
