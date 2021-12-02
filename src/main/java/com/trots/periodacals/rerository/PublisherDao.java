package com.trots.periodacals.rerository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface PublisherDao {
    Map<String, Integer> findAllPublishers(Connection con) throws SQLException;

    Integer insertPublisher(String publisher, String telephone, Connection con) throws SQLException;
}
